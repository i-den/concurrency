package jkv;

public class J02_Stopping_Threads {

    public static void main(String[] args) {
        MyRunnable runnable = getMyRunnable();
        Thread thread = new Thread(runnable, "Thread 1");

        thread.start();
        try {
            Thread.sleep(5000);
            runnable.setRunning(false);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static MyRunnable getMyRunnable() {
        return new MyRunnable();
    }
}

class MyRunnable implements Runnable {

    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Running " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
