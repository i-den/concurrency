package jkv.J01_Threads;

public class J01_Creating_Threads {

    public static void main(String[] args) {
        Thread old = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Zdr");
            }
        }, "Thread Name 0");

        Thread inline = new Thread(() -> System.out.println("Zdr"), "Thread Name 1");
        Thread fromMethod = new Thread(getRunnable(), "Thread Name 2");

        Thread current = Thread.currentThread();

    }

    private static Runnable getRunnable() {
        return () -> System.out.println("Zdr");
    }
}
