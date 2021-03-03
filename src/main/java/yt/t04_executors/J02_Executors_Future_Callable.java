package yt.t04_executors;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class J02_Executors_Future_Callable {

    public static void main(String[] args) {
        ExecutorService taskExecutor = Executors.newFixedThreadPool(3);
        CompletionService<Result> taskCompletedExecutor = new ExecutorCompletionService<>(taskExecutor);

        int tasks = 5;
        for (int i = 0; i < tasks; i++) {
            taskCompletedExecutor.submit(new CallableTask(String.valueOf(i), i * 10, (i * 10) + 10));
            System.out.println("Task " + i + " submitted");
        }

        for (int handledTasks = 0; handledTasks < tasks; handledTasks++) {
            try {
                System.out.println("Trying to take completed tasks");
                Future<Result> completedTask = taskCompletedExecutor.take();
                System.out.println("Result for a task available in the queue. Trying to get()");
                // above call blocks till at least one task is completed and results available for it
                // but we dont have to worry which one

                Result r = completedTask.get();
                System.out.println("Task " + handledTasks + ": " + r.result);

            } catch (InterruptedException e) { // taskCompletedExecutor.take();
                e.printStackTrace();
            } catch (ExecutionException e) { // completedTask.get()
                e.printStackTrace();
            }
        }
        taskExecutor.shutdown();
        System.out.println("Ended");
    }

    static class Result {
        long result;

        Result(long result) {
            this.result = result;
        }
    }

    static class CallableTask implements Callable<Result> {
        String taskName;
        long inputA;
        int inputB;

        CallableTask(String taskName, long inputA, int inputB) {
            this.taskName = taskName;
            this.inputA = inputA;
            this.inputB = inputB;
        }

        @Override
        public Result call() throws Exception {
            System.out.println("Starting: " + taskName + " --------");
            for (int i = 0; i < inputB; i++) {
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    System.out.println("Task " + taskName + " interrupted !!!!");
                    e.printStackTrace();
                }
                inputA += i;
            }
            System.out.println("Completed: " + taskName + " @@@@@@@@");

            return new Result(inputA);
        }
    }
}
