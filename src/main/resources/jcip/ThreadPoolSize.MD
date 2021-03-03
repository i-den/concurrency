The ideal size for a thread pool depends on the types of tasks that will be submitted
and the characteristics of the deployment system. Thread pool sizes should
rarely be hard-coded; instead pool sizes should be provided by a configuration
mechanism or computed dynamically by consulting Runtime.availableProcessors

Sizing thread pools is not an exact science, but fortunately you need only
avoid the extremes of “too big” and “too small”. 

If a thread pool is too big,
then threads compete for scarce CPU and memory resources, resulting in higher
memory usage and possible resource exhaustion. If it is too small, throughput
suffers as processors go unused despite available work.

### Types
```java
public ThreadPoolExecutor(int corePoolSize,
                          int maximumPoolSize,
                          long keepAliveTime,
                          TimeUnit unit,
                          BlockingQueue<Runnable> workQueue,
                          ThreadFactory threadFactory,
                          RejectedExecutionHandler handler) { ... }
```

- corePoolSize 
  - target size, attempts to maintain even if there are no tasks
- maximumPoolSize
  - upper bound on how many pool threads can be active at once
- keepAliveTime
  - thread that has been idle for longer than the keep-alive time becomes a candidate
    for reaping and can be terminated if the current pool size exceeds the core size
- Queues can help smooth out transient bursts of tasks, but if tasks continue
  to arrive too quickly you will eventually have to throttle the arrival rate to avoid
  running out of memory
- The default thread factory creates a new, nondaemon
  thread with no special configuration. Specifying a thread factory allows you to
  customize the configuration of pool threads

newFixedThreadPool and newSingleThreadExecutor use an unbounded LinkedBlockingQueue

newFixedThreadPool
 - The newFixedThreadPool factory sets both the core pool size and the maximum
   pool size to the requested pool size, creating the effect of infinite timeout
 - A fixed-size thread pool creates threads as tasks are submitted,
up to the maximum pool size, and then attempts to keep the pool
size constant (adding new threads if a thread dies due to an unexpected
Exception)

newCachedThreadPool
 - sets the maximum pool size to Integer.MAX_VALUE and the core pool size to zero with a timeout of one minute, creating the
   effect of an infinitely expandable thread pool that will contract again when demand
   decreases
 - A cached thread pool has more flexibility to reap idle
threads when the current size of the pool exceeds the demand for processing,
and to add new threads when demand increases, but places no bounds
on the size of the pool.

newSingleThreadExecutor
 - A single-threaded executor creates a single worker
thread to process tasks, replacing it if it dies unexpectedly. Tasks are guaranteed
to be processed sequentially according to the order imposed by the
task queue (FIFO, LIFO, priority order)

newScheduledThreadPool
 - A fixed-size thread pool that supports delayed and periodic task execution, similar to Timer


With a bounded work queue, the queue size and pool size must be tuned
together. A large queue coupled with a small pool can help reduce memory
usage, CPU usage, and context switching, at the cost of potentially constraining
throughput

A SynchronousQueue is not really a queue at all, but a mechanism
for managing handoffs between threads. In order to put an element on a SynchronousQueue,
another thread must already be waiting to accept the handoff. If
no thread is waiting but the current pool size is less than the maximum, Thread-
PoolExecutor creates a new thread; otherwise the task is rejected according to
the saturation policy.

The newCachedThreadPool factory is a good default choice for an Executor,
providing better queuing performance than a fixed thread pool.
A fixed size thread pool is a good choice when you need to limit the
number of concurrent tasks for resource-management purposes, as in a
server application that accepts requests from network clients and would
otherwise be vulnerable to overload

