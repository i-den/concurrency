package t03_concepts.Executors;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Executor1 {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        executorService.execute(getRunnable("Task 1"));
        executorService.execute(getRunnable("Task 2"));
        executorService.execute(getRunnable("Task 3"));
        executorService.execute(getRunnable("Task 4"));
        executorService.execute(getRunnable("Task 5"));

        executorService.shutdown();
    }

    static Runnable getRunnable(String msg) {
        return () -> {
            System.out.println(Thread.currentThread().getName() + ": " + msg);
        };
    }
}
