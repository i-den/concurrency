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
   
#### Daemon or Worker threads
#### Daemon
 - low priority thread that runs in the background
 - terminated by JVM when all other worker threads are terminated
 
 Shared heap memory, own stack memory
 
#### Race Conditions
 - occurs in critical sections (code that is executed concurrently by many threads and the seq of exec makes a diff in outcome)
 - Two types:
   - Read-modify-write
   - Check-then-act

```java
public class Counter { // Read-modify-write

   protected long count = 0;

   public void add(long value){
       this.count = this.count + value;
   }
}
```
```java
public class CheckThenActExample {

    public void checkThenAct(Map<String, String> sharedMap) {
        if(sharedMap.containsKey("key")){
            String val = sharedMap.remove("key");
            if(val == null) {
                System.out.println("Value for 'key' was null");
            }
        } else {
            sharedMap.put("key", "value");
        }
    }
}
```

Race conditions can be avoided by proper thread synchronization in critical sections. Thread synchronization can be achieved using a synchronized block of Java code. Thread synchronization can also be achieved using other synchronization constructs like locks or atomic variables like java.util.concurrent.atomic.AtomicInteger.
 
#### Local Variables
Local variables are stored in each thread's own stack. That means that local variables are never shared between threads. That also means that all local primitive variables are thread safe. Here is an example of a thread safe local primitive variable:
```java
public void someMethod(){
  int threadSafeInt = 0;
  threadSafeInt++;
}
```

##### Local Object Reference

If an object created locally never escapes the method it was created in, it is thread safe. In fact you can also pass it on to other methods and objects as long as none of these methods or objects make the passed object available to other threads.
```java
public void someMethod(){
  LocalObject localObject = new LocalObject();
  localObject.callMethod();
  method2(localObject);
}

public void method2(LocalObject localObject){
  localObject.setValue("value");
}
```

##### Local Object Member Variables - bad
```java
public class NotThreadSafe {
    StringBuilder builder = new StringBuilder();

    public add(String text){
        this.builder.append(text);
    }
}
```

Priority 0 - 10 (5 default)
