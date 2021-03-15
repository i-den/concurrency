## Thread
State

 
Properties
 - Priority (0 - 10, default 5) (MIN|NORM|MAX)
 - Name
 - State
   - new
   - runnable
   - blocked (waiting for monitor lock)
   - waiting / timed waiting
   - terminated
 - Identifier (unique long val within JVM)
 - Thread Group 
 
Daemon
 - threadInstance.setDaemon(true)
 - garbage collector is daemon thread
 - does not block JVM from stopping
 - dies when all other worker threads are stopped
 
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