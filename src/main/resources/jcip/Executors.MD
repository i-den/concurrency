The primary abstraction for task execution in the Java class libraries is not Thread, but Executor
```java
public interface Executor {
    void execute(Runnable command);
}
```

- Thread Pools manage multiple worker threads
- bound to a work queue
- better utilization when reusing threads instead of recreating them

Lifecycle - ExecutorService has three states—running, shutting down, and terminated

### Runnable Callable Future
The Executor framework uses Runnable as its basic task representation. Runnable
is a fairly limiting abstraction; run cannot return a value or throw checked
exceptions, although it can have side effects such as writing to a log file or placing
a result in a shared data structure.

Runnable and Callable describe abstract computational tasks

The submit
methods in ExecutorService all return a Future, so that you can submit a Runnable
or a Callable to an executor and get back a Future that can be used to
retrieve the result or cancel the task

You can also explicitly instantiate a FutureTask
for a given Runnable or Callable. (Because FutureTask implements
Runnable, it can be submitted to an Executor for execution or executed directly
by calling its run method.)

CompletionService combines the functionality of an Executor and a BlockingQueue.
You can submit Callable tasks to it for execution and use the queuelike
methods take and poll to retrieve completed results, packaged as Futures,
as they become available. ExecutorCompletionService implements Completion-
Service, delegating the computation to an Executor.

With CompletionService, this is pretty much automated. The difference is not very evident in the code you have presented because you are submitting just one task. However, imagine you have a list of tasks to be submitted. In the example below, multiple tasks are submitted to the CompletionService. Then, instead of trying to find out which task has completed (to get the results), it just asks the CompletionService instance to return the results as they become available.

```java

```