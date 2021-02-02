public class RunnerDemo {

    public static void main(String[] args) {
        Thread t1 = new Thread(new RunnerA());
        Thread t2 = new Thread(new RunnerB());

        t1.start();
        t2.start();
    }
}

class RunnerA implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("RunnerA : " + i);
        }
    }
}

class RunnerB implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("RunnerB : " + i);
        }
    }
}