package jkv.J01_Threads;

public class J08_ThreadLocal {

    public static void main(String[] args) {
        init();
        threadLocalUsage();
        inheritable();
    }

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
         */}

    private static void init() {
        ThreadLocal<String> tl = new ThreadLocal<>();
        tl.set("String");
    }

    private static void threadLocalUsage() {
        RunnableHolder runnableHolder = new RunnableHolder();
        Thread t1 = new Thread(runnableHolder);
        Thread t2 = new Thread(runnableHolder);
        Thread t3 = new Thread(runnableHolder);

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

class RunnableHolder implements Runnable {
    private ThreadLocal<Double> number = new ThreadLocal<>();

    @Override
    public void run() {
        number.set(Math.random() * 100);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + ": " + number.get());
    }
}
