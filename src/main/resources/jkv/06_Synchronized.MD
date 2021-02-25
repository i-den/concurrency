#### Synchronized
- obtains the intrinsic lock (monitor) of the object that it's synchronizing on (mutex)
- can be used on


```java
// instance methods
public synchronized void doSomething() {}

// static methods
public static synchronized void doSomethingElse() {}

// blocks inside instance methods
public void doSomething2() {
    synchronized(lock) {
        // critical section
    }
}

// blocks inside static methods
public void doSomethingElse2() {
    synchronized(MyClass.class) {
        // critical section
    }
}
```

Can be used in lambdas
```java
  public static void main(String[] args) {
    Consumer<String> func = (String param) -> {
      synchronized(SynchronizedExample.class) {}
    };
  }
```

Synchronized blocks are re-entrant. This means that a Thread that already holds a monitor lock can enter other synchronized blocks that synchronize on the same lock
```java
class Reentrance {
    int count = 0;
    
    public synchronized void inc() {
        count++;
    }
    
    public synchronized int incAndGet() {
        inc();          // requires the lock again
        return count;
    }
}
```

#### Synchronized Visibility Guarantee
```java
class Counter {
    private int count = 0;

    public void inc() {
        synchronized (this) { // gets count from main memory
            count++;   
        }                     // saves count back to main memory
    }

    public long get() {
        synchronized (this) { // gets count from main memory
            return count;
        }
    }
}
```

#### Overhead
- low when sync block is unconstested
- higher when contested - already occupied by another thread