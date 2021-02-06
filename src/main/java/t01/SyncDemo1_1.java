package t01;

public class SyncDemo1_1 {
    private static int cnt = 0;

    public static void main(String[] args){
        for (int i = 0; i < 10; i++) {
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

            if (cnt != 20000)
                System.out.println(cnt);
            cnt = 0;
        }
    }

    static class T1 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i <= 10000; i++) {
                cnt++;
            }
        }
    }

    static class T2 implements Runnable {
        @Override
        public void run() {
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 1; i <= 10000; i++) {
                cnt++;
            }
        }
    }
}
