
 
#### Intrinsic lock
Class level
```
public synchronized void method() {}

synchronized(MyClass.class) { } 
```
Object level
```
public synchronized(this) {}

synchronized(this) {}
```

Object locking
```java
class C {
    private static final Object obj = new Object();

    public static void main(String[] args){
      synchronized (obj) {
          // ...
      }
    }
}
```

#### Wait & Notify


#### Race Conditions
 - two or more threads access the same section and the outcome is non-deterministic, its based on how the threads get scheduled to access them
 
#### Locks
Reentrant locks, tryLock
  - synchronized - single block, locks - called from multiple places
  - lock() and unlock() provides same visibility and happens before guarantee as synchronized blocks
  - synchronized blocks are always reentrant, locks can not be
  - locks can guarantee fairness, synchronized blocks can't
#### ExecutorService