package t01;

public class RunnerDemo {

    public static void main(String[] args) {
        Thread t1 = new RunnerA();
        Thread t2 = new Thread(new RunnerB());

        t1.start();
        t2.start();
        System.out.println("Main Method");
    }
}

class RunnerA extends Thread {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t01.RunnerA : " + i);
        }
    }
}

class RunnerB implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("t01.RunnerB : " + i);
        }
    }
}