package general;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GC02_Race_Conditions {

    public static void main(String[] args) {
        readModifyWrite();
        checkThenAct();
    }

    private static void readModifyWrite() {
        Read_Modify_Write readModifyWrite = new Read_Modify_Write();
        Thread t1 = new Thread(readModifyWrite, "Thread 1");
        Thread t2 = new Thread(readModifyWrite, "Thread 2");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(readModifyWrite.getCount());
    }

    private static void checkThenAct() {
        Check_then_Act checkThenAct1 = new Check_then_Act();
        Thread t1 = new Thread(checkThenAct1);
        Thread t2 = new Thread(checkThenAct1);
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Read_Modify_Write implements Runnable {
    private int count = 0;

    @Override
    public void run() {
        for (int i = 0; i < 1_000_000; i++) {
            count++;
        }
        System.out.println(Thread.currentThread().getName() + ": " + count);
    }

    public int getCount() {
        return count;
    }
}

class Check_then_Act implements Runnable {
    private static Map<String, String> map = new ConcurrentHashMap<>();

    @Override
    public void run() {
        for (int i = 0; i < 1_000; i++) {
            if (map.containsKey("Key")) {
                String value = map.remove("Key");
                if (value == null) {
                    System.out.println("Slipped");
                }
            } else {
                map.put("Key", "Value");
            }
        }
    }
}