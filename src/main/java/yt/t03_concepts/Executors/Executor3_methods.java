package yt.t03_concepts.Executors;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Executor3_methods {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<?> future = executorService.submit(getRunnable("Task 1"));

        System.out.println(future.isDone());

        try {
            future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println(future.isDone());

        executorService.shutdown();
    }

    static Runnable getRunnable(String msg) {
        return () -> System.out.println(Thread.currentThread().getName() + ": " + msg);
    }
}
