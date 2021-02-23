package yt.t03_concepts.Executors;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Executor2_constructors {

    public static void main(String[] args) {
        int corePoolSize = 5;
        int maxPoolSize = 10;
        long keepAliveTime = 3000;
        BlockingQueue<Runnable> taskQueue = new ArrayBlockingQueue<>(128);

        ExecutorService executorService1 = new ThreadPoolExecutor(
                corePoolSize,
                maxPoolSize,
                keepAliveTime,
                TimeUnit.MILLISECONDS,
                taskQueue
        );

        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
    }
}
