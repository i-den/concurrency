Both Processes and Threads are independent sequences of execution

#### Process
- instance of program execution

#### Thread
- light-weight processT
- a unit of execution withing a given processT

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
   
#### t01.Daemon or t01.Worker threads
#### t01.Daemon
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

