#### Concurrency
Concurrency means that an application is making progress on more than one task - at the same time or at least seemingly at the same time

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/02_Concurrency_vs_Parallelism/01_Concurrency.png">

#### Parallelism
The term parallelism means that an application splits its tasks up into smaller subtasks which can be processed in parallel, for instance on multiple CPUs at the exact same time. Thus, parallelism does not refer to the same execution model as parallel concurrent execution - even if they may look similar on the surface.

The diagram below illustrates a bigger task which is being split up into 4 subtasks. These 4 subtasks are being executed by 4 different threads, which run on 2 different CPUs. This means, that parts of these subtasks are executed concurrently (those executed on the same CPU), and parts are executed in parallel (those executed on different CPUs).

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/02_Concurrency_vs_Parallelism/04_Task_Split.png">

#### Parallel execution

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/02_Concurrency_vs_Parallelism/02_Parallelism.png">


<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/02_Concurrency_vs_Parallelism/03_Parallel_Concurrent_Execution.png">
