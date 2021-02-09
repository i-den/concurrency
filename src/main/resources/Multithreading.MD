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
 - happens before
 
 If the boolean is not volatile there will be visibility issues
[01_volatile]
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
[02_volatile]
 - writing to a volatile variable will cause that variable and all other variables visible to the thread to be written in the main memory
 - when a volatile variable is read this is from the main memory, any other variables visible to the thread will also be refreshed from the main memory
 
Reordering
 - compiler and JVM can re-order the structure as long as it keeps its semantic meaning
[03_volatile]
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
