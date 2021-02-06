package t02_wait_notify;

public class t02_01_wait_notify {

    public static void main(String[] args) {
        ProcessT processT = new ProcessT();
        Thread produce = new Thread(() -> {
            try {
                processT.produce();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consume = new Thread(() -> {
            try {
                processT.consume();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        produce.start();
        consume.start();
    }
}

class ProcessT {

    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("Produce 1");
            wait();
            System.out.println("Produce 2");
        }
    }

    public void consume() throws InterruptedException {
        synchronized (this) {
            Thread.sleep(1000);
            System.out.println("Consume 1");
            notify(); // not handing over the lock immediately
            Thread.sleep(5000);
            System.out.println("After notify");

        }
    }
}
