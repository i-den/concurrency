package t02_wait_notify;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Locks {

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
            Procezz p = new Procezz();

            Thread t1 = new Thread(p::increment);
            Thread t2 = new Thread(p::increment);

            t1.start();
            t2.start();

            try {
                t1.join();
                t2.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (p.getCount() != 20000)
                System.out.println(p.getCount());
        }
    }
}

class Procezz {
    private int count = 0;
    private Lock lock = new ReentrantLock(true);

    l
    
    public void increment() {
       lock.lock(); // <-- here
        try {
            for (int i = 0; i < 10000; i++) {
                count++;
            }
        } finally {
           lock.unlock(); // <-- here
        }
    }

    public Lock getLock() {
        return lock;
    }

    public int getCount() {
        return count;
    }
}
