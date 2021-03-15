Future:
- It cannot be manually completed
- You cannot perform further action on a Future’s result without blocking
  - .get() blocks
- Multiple Futures cannot be chained together
- You cannot combine multiple Futures together 
- No Exception Handling

CompletableFuture
- CompletableFuture executes tasks in a thread obtained from the global ForkJoinPool.commonPool()
- You can also create a Thread Pool and pass it to runAsync() and supplyAsync() methods to let them execute their tasks in a thread obtained from your thread pool

.get() is blocking. You can run CompletableFuture without blocking
- thenApply()
- thenAccept()
- thenRun()



