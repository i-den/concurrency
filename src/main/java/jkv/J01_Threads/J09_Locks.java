package jkv.J01_Threads;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class J09_Locks {

    public static void main(String[] args) {
        basicUsage();
        lockExamples();


    }

    private static void basicUsage() {
        boolean fairness = true;
        Lock lock = new ReentrantLock(fairness);
        lock.lock();
        // critical section
        lock.unlock();
    }

    private static void lockExamples() {
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
}

class Increment {
    private int n = 0;
    private Lock lock = new ReentrantLock(true);

    public void inc() {
        try {
            lock.lock();
            n++;
        } finally {
            lock.unlock();
        }
    }

    public int getN() {
        try {
            lock.lock();
            return n;
        } finally {
            lock.unlock();
        }
    }
}

class ReadWriteLockExample {
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock readLock = readWriteLock.readLock();
    private final Lock writeLock = readWriteLock.writeLock();

    private Map<String, String> map = new HashMap<>();

    public void put(String key, String value) {
        try {
            writeLock.lock();
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }

    public String get(String key) {
        try {
            readLock.lock();
            return map.get(key);
        } finally {
            readLock.unlock();
        }
    }
}