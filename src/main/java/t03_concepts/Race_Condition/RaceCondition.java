package t03_concepts.Race_Condition;

public class RaceCondition {

    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(getRunnable(counter, "T1: "));
        Thread t2 = new Thread(getRunnable(counter, "T2: "));

        t1.start();
        t2.start();
    }

    private static Runnable getRunnable(Counter counter, String msg) {
        return () -> {
            for (int i = 0; i < 1_000_000; i++) {
                counter.incAndGet();
            }
            System.out.println(msg + counter.getCount());
        };
    }
}

class Counter {
    private long count = 0;

    public long incAndGet() {
        count++;
        return count;
    }

    public long getCount() {
        return count;
    }
}