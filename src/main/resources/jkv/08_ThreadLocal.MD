#### ThreadLocal
Each thread will have a local copy of a ThreadLocal variable

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/08_ThreadLocal/01_ThreadLocal.png">

```java
ThreadLocal<String> tl = new ThreadLocal<>();
tl.set("String");
String str = tl.get();
```

Initial ThreadLocal
```java
private ThreadLocal myThreadLocal = new ThreadLocal<String>() {
    @Override
    protected String initialValue() {
        return String.valueOf(System.currentTimeMillis());
    }
};

ThreadLocal<String> threadLocal = ThreadLocal.withInitial(new Supplier<String>() {
    @Override
    public String get() {
        return String.valueOf(System.currentTimeMillis());
    }
});

ThreadLocal threadLocal2 = ThreadLocal.withInitial(
        () -> String.valueOf(System.currentTimeMillis()) );
```

