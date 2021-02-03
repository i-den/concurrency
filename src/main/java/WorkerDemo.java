public class WorkerDemo {

    public static void main(String[] args) {
        Thread d1 = new Thread(new Daemon());
        Thread w1 = new Thread(new Worker());

        d1.setDaemon(true);

        d1.start();
        w1.start(); // d1 will die once w1 dies
    }
}

class Worker implements Runnable {

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Worker#run on thread:" + Thread.currentThread().getName());
    }
}

class Daemon implements Runnable {

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Daemon#run on thread: " + Thread.currentThread().getName());
        }
    }
}