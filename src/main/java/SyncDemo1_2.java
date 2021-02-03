public class SyncDemo1_2 {
    private static int cnt = 0;

    public static synchronized void increment() {
        cnt++;
    }

    public static void main(String[] args){
        Thread t1 = new Thread(new T1());
        Thread t2 = new Thread(new T2());

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(cnt);
    }

    static class T1 implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 1000; i++) {
                increment();
            }
        }
    }

    static class T2 implements Runnable {
        @Override
        public void run() {
            for (int i = 1; i <= 1000; i++) {
               increment();
            }
        }
    }
}
