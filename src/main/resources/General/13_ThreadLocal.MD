## ThreadLocal

- think of Map<Thread, Value>
- local variables for Threads

```java
ThreadLocal<String> tl = new ThreadLocal<>();
tl.set("String");
tl.get();
tl.remove();

ThreadLocal<String> tl2 = ThreadLocal.withInitial(() -> {
    return new "Initial Value";
});
```

There's also an InheritableThreadLocal
```java
private static void inheritable() {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
        inheritableThreadLocal.set(Math.random() + "");

        Thread t1 = new Thread(() -> {
            System.out.println("Thread 1");
            threadLocal.set("Thread 1 - TL");
            inheritableThreadLocal.set("Thread 1 - Inherited");

            System.out.println(threadLocal.get());
            System.out.println(inheritableThreadLocal.get());

            Thread childThread = new Thread(() -> {
                System.out.println("Child Thread");
                System.out.println(threadLocal.get());
                System.out.println(inheritableThreadLocal.get());
            });
            childThread.start();
        });
        t1.start();
        /*
         * Thread 1
         * Thread 1 - TL
         * Thread 1 - Inherited
         * Child Thread
         * null
         * Thread 1 - Inherited
         */
}
```