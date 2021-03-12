package yt.t04_executors;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class J01_Basic_Executors {

    public static void main(String[] args) {
        ExecutorService exec = Executors.newFixedThreadPool(3);

        Future<String> f1 = exec.submit(getCallable("Msg 1"));
        Future<String> f2 = exec.submit(getCallable("Msg 2"));
        Future<String> f3 = exec.submit(getCallable("Msg 3"));
        Future<String> f4 = exec.submit(getCallable("Msg 4"));
        Future<String> f5 = exec.submit(getCallable("Msg 5"));
        Future<String> f6 = exec.submit(getCallable("Msg 6"));

        System.out.println("F1 Complete: " + f1.isDone());
        System.out.println("F2 Complete: " + f2.isDone());
        System.out.println("F3 Complete: " + f3.isDone());
        System.out.println("F4 Complete: " + f4.isDone());
        System.out.println("F5 Complete: " + f5.isDone());
        System.out.println("F6 Complete: " + f6.isDone());

        try {
            System.out.println(f1.get());
            System.out.println(f2.get());
            System.out.println(f3.get());
            System.out.println(f4.get());
            System.out.println(f5.get());
            System.out.println(f6.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        exec.shutdown();
    }

    private static Callable<String> getCallable(final String msg) {
        return () -> Thread.currentThread().getName() + ": " + msg;
    }
}
