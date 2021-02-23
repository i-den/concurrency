package yt.t03_concepts.Locks;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Lock1 {

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 100; i++) {
            Counter c = new Counter();

            Thread t1 = new Thread(getRunnable(c));
            Thread t2 = new Thread(getRunnable(c));

            t1.start();
            t2.start();

            t1.join();
            t2.join();

            int count = c.getCount();
            if (count != 2_000_000) {
                System.out.println(c.getCount());
            }
        }
    }

    void lockExamples() {
        boolean fairness = true;
        ReentrantLock lock = new ReentrantLock(fairness);
        int holdCount = lock.getHoldCount();
        int queueLength = lock.getQueueLength();
        boolean hasQueuedThisThread = lock.hasQueuedThread(Thread.currentThread());
        boolean hasQueuedThreads = lock.hasQueuedThreads();
        boolean isFair = lock.isFair();
        boolean isLocked = lock.isLocked();
        boolean isHeldByCurrentThread = lock.isHeldByCurrentThread();
    }

    static Runnable getRunnable(Counter c) {
        return () -> {
            for (int i = 0; i < 1_000_000; i++) {
                c.increment();
            }
        };
    }
}

class Counter {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    public void increment() {
        try {
            lock.lock();
            count++;
        } finally {
            lock.unlock();
        }
    }

    public int getCount() {
        try {
            lock.lock();
            return count;
        } finally {
            lock.unlock();
        }
    }
}
