#### Locks
Reentrant locks, tryLock
  - synchronized - single block, locks - called from multiple places
  - lock() and unlock() provides same visibility and happens before guarantee as synchronized blocks
  - synchronized blocks are always reentrant, locks can not be
  - locks can guarantee fairness, synchronized blocks can't
  
```java
ReentrantLock lock = new ReentrantLock();
int count = 0;

void increment() {
    lock.lock();
    try {
        count++;
    } finally {
        lock.unlock();
    }
}
```

#### Read/Write Locks
Read Access   	If no threads are writing, and no threads have requested write access.

Write Access   	If no threads are reading or writing.

#### StampedLock
```java
ExecutorService executor = Executors.newFixedThreadPool(2);
StampedLock lock = new StampedLock();

executor.submit(() -> {
    long stamp = lock.tryOptimisticRead();
    try {
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
        sleep(1);
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
        sleep(2);
        System.out.println("Optimistic Lock Valid: " + lock.validate(stamp));
    } finally {
        lock.unlock(stamp);
    }
});

executor.submit(() -> {
    long stamp = lock.writeLock();
    try {
        System.out.println("Write Lock acquired");
        sleep(2);
    } finally {
        lock.unlock(stamp);
        System.out.println("Write done");
    }
});

stop(executor);
```
```java
public class StampedLockDemo {
    Map<String,String> map = new HashMap<>();
    private StampedLock lock = new StampedLock();

    public void put(String key, String value){
        long stamp = lock.writeLock();
        try {
            map.put(key, value);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    public String get(String key) throws InterruptedException {
        long stamp = lock.readLock();
        try {
            return map.get(key);
        } finally {
            lock.unlockRead(stamp);
        }
    }
}
```
```java
public String readWithOptimisticLock(String key) {
    long stamp = lock.tryOptimisticRead();
    String value = map.get(key);

    if(!lock.validate(stamp)) {
        stamp = lock.readLock();
        try {
            return map.get(key);
        } finally {
            lock.unlock(stamp);               
        }
    }
    return value;
}
```

#### Conditions
Traditionally Java provides wait(), notify() and notifyAll() methods for thread intercommunication. Conditions have similar mechanisms, but in addition, we can specify multiple conditions
```java
public class ReentrantLockWithCondition {

    Stack<String> stack = new Stack<>();
    int CAPACITY = 5;

    ReentrantLock lock = new ReentrantLock();
    Condition stackEmptyCondition = lock.newCondition();
    Condition stackFullCondition = lock.newCondition();

    public void pushToStack(String item){
        try {
            lock.lock();
            while(stack.size() == CAPACITY) {
                stackFullCondition.await();
            }
            stack.push(item);
            stackEmptyCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public String popFromStack() {
        try {
            lock.lock();
            while(stack.size() == 0) {
                stackEmptyCondition.await();
            }
            return stack.pop();
        } finally {
            stackFullCondition.signalAll();
            lock.unlock();
        }
    }
}
```

## Lock Striping
The approach of defining exclusive access over data structures with a single lock object is called coarse-grained synchronization.

In a coarse-grained synchronization implementation, every access to the object must be made at a time by one thread. We end up having sequential accesses.

Our goal is to allow concurrent threads to work on the data structure while ensuring thread-safety.

To reach our goal, we'll use the Lock Striping pattern. Lock striping is a technique where the locking occurs on several buckets or stripes, meaning that accessing a bucket only locks that bucket and not the entire data structure.

There are a couple of ways to do this:

 - First, we could use a lock per task, thus maximizing concurrency between tasks – this has a higher memory footprint, though
 - Or, we could use a single lock for every task, which makes use of less memory but also compromises performance in concurrency
To help us manage this performance-memory tradeoff, Guava ships with a class called Striped. It's similar to logic found in ConcurrentHashMap, but the Striped class goes even further by reducing the synchronization of distinct tasks using semaphores or reentrant locks.

```java
public class StripedLock extends ConcurrentAccessExperiment {
    Striped lock;

    public StripedLock(int buckets) {
        lock = Striped.lock(buckets);
    }

    protected Supplier<?> putSupplier(Map<String,String> map, int key) {
        return (()-> {
            int bucket = key % stripedLock.size();
            Lock lock = stripedLock.get(bucket);
            lock.lock();
            try {
                return map.put("key" + key, "value" + key);
            } finally {
                lock.unlock();
            }
        });
    }

    protected Supplier<?> getSupplier(Map<String,String> map, int key) {
        return (()-> {
            int bucket = key % stripedLock.size();
            Lock lock = stripedLock.get(bucket);
            lock.lock(); 
            try {
                return map.get("key" + key);
            } finally {
                lock.unlock();
            }
        });
    }
}
```

If multiple threads will access a Collections.synchronizedMap() frequently, there will be a lot of contention since each method is synchronized using a shared lock (i.e. if thread X calls a method on a Collections.synchronizedMap(), all other threads will be blocked from calling any method on a Collections.synchronizedMap() until thread X returns from the method it called).

A ConcurrentHashMap has a variable number of locks (default is 16) that each guard a segment of the keys in the ConcurrentHashMap. So for a ConcurrentHashMap with 160 keys, each lock will guard 10 elements. Therefore, methods operating on a key (get, put, set, etc...) only lock out access to other methods operating on a key where the keys are in the same segment. For example, if thread X calls put(0, someObject), and then thread Y calls put(10, someOtherObject) those calls can execute concurrently, and thread Y does not have to wait for thread X to return from put(0, someObject)
