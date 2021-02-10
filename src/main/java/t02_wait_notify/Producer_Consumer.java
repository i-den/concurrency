package t02_wait_notify;

import java.util.ArrayList;
import java.util.List;

public class Producer_Consumer {

    public static void main(String[] args) {
        Process p = new Process();

        Thread producer = new Thread(() -> {
            try {
                p.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                p.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        producer.start();
        consumer.start();
    }
}

class Process {
    private static final int UPPER_BOUND = 5;
    private static final int LOWER_BOUND = 0;

    private final List<Integer> numbers = new ArrayList<>(5);
    private int value = 1;

    public void produce() throws InterruptedException {
        synchronized (this) {
            while (true) {
                if (numbers.size() == UPPER_BOUND) {
                    System.out.println("Nulling value on thread " + Thread.currentThread().getName());
                    value = 1;
                    wait();
                } else {
                    System.out.println("Adding " + value + " on thread " + Thread.currentThread().getName());
                    numbers.add(value++);
                    notify(); // nothing happens when the other thread is not waiting, needs to release the intrinsic lock
                    // as notify is in a loop it will continue the execution, the other thread is notified already
                    // and once this sleeps, the other one will start working
                }
                Thread.sleep(500);
            }
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            while (true) {
                if (numbers.size() == LOWER_BOUND) {
                    System.out.println("No items present on thread " + Thread.currentThread().getName());
                    wait();
                } else {
                    System.out.println("Removing " + numbers.remove(numbers.size() - 1) + " on thread " + Thread.currentThread().getName());
                    notify();
                }
                Thread.sleep(500);
            }
        }
    }
}
