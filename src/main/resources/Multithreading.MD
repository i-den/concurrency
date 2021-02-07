Both Processes and Threads are independent sequences of execution

#### Process
- instance of program execution

#### Thread
- light-weight process
- a unit of execution withing a given process

Processing time for a single core is shared among processes and threads - time slicing

Parallel - multiple cores each running a thread continuously

#### Thread States
 - new
   - when instantiated
   - until .start() is called
 - runnable
   - after we have started the thread
   - thread is executing a task in this state
 - waiting
   - wait() and sleep()
 - dead
   - after task is finished
   
#### Daemon or .Worker threads
#### Daemon
 - low priority thread that runs in the background
 - terminated by JVM when all other worker threads are terminated
 
 Shared heap memory, own stack memory
 
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


#### Volatile
 - every read of a volatile variable will be read from the computer's main memory
 - visibility problem: the updates of one thread are not visible to other threads
 - http://tutorials.jenkov.com/java-concurrency/volatile.html
 