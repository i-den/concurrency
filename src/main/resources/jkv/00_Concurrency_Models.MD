#### Shared State
Shared state means that the different threads in the system will share some state among them. By state is meant some data, typically one or more objects or similar. When threads share state, problems like race conditions and deadlock etc. may occur. It depends on how the threads use and access the shared objects, of course.

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/00_Concurrency_Models/01_Shared_State.png">

#### Separate State
Separate state means that the different threads in the system do not share any state among them. In case the different threads need to communicate, they do so either by exchanging immutable objects among them, or by sending copies of objects (or data) among them. Thus, when no two threads write to the same object (data / state), you can avoid most of the common concurrency problems
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/00_Concurrency_Models/02_Separate_State.png">

### Parallel Workers
In the parallel worker concurrency model a delegator distributes the incoming jobs to different workers. Each worker completes the full job. The workers work in parallel, running in different threads, and possibly on different CPUs.
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/00_Concurrency_Models/03_Parallel_Workers.png">

The advantage of the parallel worker concurrency model is that it is easy to understand. To increase the parallelization of the application you just add more workers.

Some of this shared state is in communication mechanisms like job queues. But some of this shared state is business data, data caches, connection pools to the database etc.

As soon as shared state sneaks into the parallel worker concurrency model it starts getting complicated. The threads need to access the shared data in a way that makes sure that changes by one thread are visible to the others (pushed to main memory and not just stuck in the CPU cache of the CPU executing the thread). Threads need to avoid race conditions, deadlock and many other shared state concurrency problems.

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/00_Concurrency_Models/04_Shared_State_Workers.png">

Additionally, part of the parallelization is lost when threads are waiting for each other when accessing the shared data structures. Many concurrent data structures are blocking, meaning one or a limited set of threads can access them at any given time

Persistent data structures are another alternative. A persistent data structure always preserves the previous version of itself when modified. Thus, if multiple threads point to the same persistent data structure and one thread modifies it, the modifying thread gets a reference to the new structure. All other threads keep a reference to the old structure which is still unchanged and thus consistent

Another disadvantage of the parallel worker model is that the job execution order is nondeterministic. There is no way to guarantee which jobs are executed first or last. Job A may be given to a worker before job B, yet job B may be executed before job A.

### "Assembly line"
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/00_Concurrency_Models/05_Assembly_Line.png">

The workers are organized like workers at an assembly line in a factory. Each worker only performs a part of the full job. When that part is finished the worker forwards the job to the next worker.

Each worker is running in its own thread, and shares no state with other workers. This is also sometimes referred to as a shared nothing concurrency model.

__Systems using the assembly line concurrency model are usually designed to use non-blocking IO. Non-blocking IO means that when a worker starts an IO operation (e.g. reading a file or data from a network connection) the worker does not wait for the IO call to finish__

With non-blocking IO, the IO operations determine the boundary between workers. A worker does as much as it can until it has to start an IO operation. Then it gives up control over the job. When the IO operation finishes, the next worker in the assembly line continues working on the job, until that too has to start an IO operation etc.
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/00_Concurrency_Models/06_Non-Blocking.png">
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/00_Concurrency_Models/07_Non-Blocking.png">
