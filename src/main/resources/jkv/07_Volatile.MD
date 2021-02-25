#### Volatile
 - every read of a volatile variable will be read from the computer's main memory
 - visibility problem: the updates of one thread are not visible to other threads
 - __If Thread A writes to a volatile variable and Thread B subsequently reads the same volatile variable, then all variables visible to Thread A before writing the volatile variable, will also be visible to Thread B after it has read the volatile variable__
 
 If the boolean is not volatile there will be visibility issues

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/07_Volatile/01_Volatile.png">

```java
class Exchanger {
    private Object object;
    private volatile boolean hasNewObject = false; // volatile var

    public void setObject(Object obj) {
        this.object = obj;
    }

    public Object getObject() {
        while(!this.hasNewObject) { // reads from main memory
            // busy wait
        }
        Object ret = this.object;
        this.hasNewObject = false;
        return ret;
    }
}
```

#### Visibility guarantee
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/07_Volatile/02_Volatile.png">

 - writing to a volatile variable will cause that variable and all other variables visible to the thread to be written in the main memory
 - when a volatile variable is read this is from the main memory, any other variables visible to the thread will also be refreshed from the main memory
 
Reordering
 - compiler and JVM can re-order the structure as long as it keeps its semantic meaning
 
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/07_Volatile/03_Volatile.png">
 
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

As I have mentioned earlier, if two threads are both reading and writing to a shared variable, then using the volatile keyword for that is not enough. You need to use a synchronized in that case to guarantee that the reading and writing of the variable is atomic. Reading or writing a volatile variable does not block threads reading or writing. For this to happen you must use the synchronized keyword around critical sections.