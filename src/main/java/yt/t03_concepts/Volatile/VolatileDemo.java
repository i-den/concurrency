package yt.t03_concepts.Volatile;

public class VolatileDemo {

    public static void main(String[] args) {
        Worker w = new Worker();
        Thread t1 = new Thread(w);
        Thread t2 = new Thread(w);

        w.setRunning(true); // issue
        t1.start();
        t2.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        w.setRunning(false); // issue
        System.out.println("End");
    }
}

class Worker implements Runnable {

    private boolean running; // could be volatile

    @Override
    public void run() {
        while (running) {
            System.out.println("Working on thread " + Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}