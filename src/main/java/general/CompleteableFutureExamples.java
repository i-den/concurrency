package general;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompleteableFutureExamples {

    public static void main(String[] args) {
        // runsForever();
        // simpleTest();
        // runsAsyncInBackground();
        // runAsyncAndThenReturnComputation();
        // nonBlockingThenApply();
        // nonBlockingThenApplyWithChain();
    }

    private static void runsForever() {
        CompletableFuture<String> cf = new CompletableFuture<>();
        try {
            // will block forever because the Future is never completed
            String s = cf.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void simpleTest() {
        // All the clients waiting for this Future will get the specified result.
        // And, Subsequent calls to completableFuture.complete() will be ignored
        CompletableFuture<String> cf = new CompletableFuture<>();
        cf.complete("str");
        try {
            System.out.println(cf.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * If you want to run some background task asynchronously and don’t want to return anything from
     * the task, then you can use CompletableFuture.runAsync() method. It takes a Runnable object
     * and returns CompletableFuture<Void>
     */
    private static void runsAsyncInBackground() {
        Runnable r = () -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(
                "Running in different Thread due to runAsync() method call. Thread: "
                + Thread.currentThread().getName()
            );
        };
        CompletableFuture<Void> f = CompletableFuture.runAsync(r);
        try {
            f.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void runAsyncAndThenReturnComputation() {
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Result computed from Thread: " + Thread.currentThread().getName();
        });
        try {
            System.out.println(f.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void nonBlockingThenApply() {
        CompletableFuture<String> whatsYourName = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Thread: " + Thread.currentThread().getName() + "First Chain";
        });
        // Attach a callback
        CompletableFuture<String> whatsYourNameChain = whatsYourName.thenApply(name ->
                                                                                   "Thread: "
                                                                                   + Thread
                                                                                       .currentThread()
                                                                                       .getName()
                                                                                   + " invoking thenApply\n "
                                                                                   + name
        );

        try {
            System.out.println(whatsYourNameChain.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static void nonBlockingThenApplyWithChain() {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "Gosho";
        })
            .thenApply(name -> {
                return "Hello " + name;
            })
            .thenApply(greeting -> {
                return greeting + ", welcome!";
            });
        try {
            System.out.println(welcomeText.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }
}
