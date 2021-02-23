package yt.t03_concepts.Race_Condition;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RaceConditionMap {

    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();

        Thread t1 = new Thread(getRunnable(map));
        Thread t2 = new Thread(getRunnable(map));

        t1.start();
        t2.start();
    }

    private static Runnable getRunnable(Map<String, String> map) {
        return () -> {
            for (int i = 0; i < 1_000; i++) {
                if (map.containsKey("key")) { // can have a race condition when two threads access the same evaluation of containsKey at the same time
                    String value = map.remove("key"); // then the first removes the key, the second receives a null from the Concurrent map
                    if (value == null) {
                        System.out.println("Null Value on Iteration : " + i);
                    }
                } else {
                    map.put("key", "value");
                }
            }
        };
    }

    private static Runnable getRunnable2(Map<String, String> map) {
        return () -> {
            for (int i = 0; i < 1_000; i++) {
                synchronized (map) { // fix here
                    if (map.containsKey("key")) {
                        String value = map.remove("key");
                        if (value == null) {
                            System.out.println("Null Value on Iteration : " + i);
                        }
                    } else {
                        map.put("key", "value");
                    }
                }
            }
        };
    }
}
