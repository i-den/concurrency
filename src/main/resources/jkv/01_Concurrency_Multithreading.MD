<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/01_Concurrency_Multithreading/01_Singletasking.png">
 - just a single task running

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/01_Concurrency_Multithreading/02_Multitasking.png">
 - each thread has its own task
 - switching between threads / time-slice
 
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/01_Concurrency_Multithreading/03_Parallel_Multithread.png">
When a CPU switches from executing one thread to executing another, the CPU needs to save the local data, program pointer etc. of the current thread, and load the local data, program pointer etc. of the next thread to execute. This switch is called a "context switch"

Both Processes and Threads are independent sequences of execution

#### Process
- instance of program execution

#### Thread
- light-weight process
- a unit of execution withing a given process

The process is used to execute something, a program. To break down the program into several execution segments something lightweight was needed - the thread.

The thread is a mean of saying I'm breaking down my process to those core parts, and the operating system then takes responsibility into scheduling their execution

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
 - happens before
 
 If the boolean is not volatile there will be visibility issues
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/01_volatile.PNG">
```java
class Exchanger {
    private Object object;
    private volatile boolean hasNewObject = false;

    public void setObject(Object obj) {
        this.object = obj;
    }

    public Object getObject() {
        while(!this.hasNewObject) {
            // busy wait
        }
        Object ret = this.object;
        this.hasNewObject = false;
        return ret;
    }
}
```



#### Visibility guarantee
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/02_volatile.PNG">
 - writing to a volatile variable will cause that variable and all other variables visible to the thread to be written in the main memory
 - when a volatile variable is read this is from the main memory, any other variables visible to the thread will also be refreshed from the main memory
 
Reordering
 - compiler and JVM can re-order the structure as long as it keeps its semantic meaning
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/03_volatile.PNG">
 - this might break the reads/writes from the memory
 
Happens Before Guarantee
 - all writes to volatile or non-volatile variables before a write to a volatile variable will remain before that write
 - all reads of a volatile variable that are before reads of other volatile or non-volatile will remain before
 
#### Volatile issues
If two threads access the count variable while its 9 and increment it, their saves will overwrite each other
```java
public class Counter {

    private volatile int count = 0;

    public boolean increment() {
        if(count == 10) {
            return false;
        }
        // not atomic
        // read count
        // increment count
        // write 
        count++;
        return true;
    }   
}
```

#### Race Conditions
 - two or more threads access the same section and the outcome is non-deterministic, its based on how the threads get scheduled to access them
 
#### Locks
Reentrant locks, tryLock
  - synchronized - single block, locks - called from multiple places
  - lock() and unlock() provides same visibility and happens before guarantee as synchronized blocks
  - synchronized blocks are always reentrant, locks can not be
  - locks can guarantee fairness, synchronized blocks can't
#### ExecutorService