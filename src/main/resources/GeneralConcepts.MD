#### General
- Process / Thread / Concurrency / Parallelism
  - context switch
- Synchronization
  - critical section
  - race condition
- Deadlock / Livelock / Busy Waiting / Starvation
- Atomic / Compare-and-Swap / Optimistic / Pessimistic locking


#### Semaphore
- rendezvous
- mutex
- multiplex
- barrier
- turnstile
- lightswitch
 
 
#### Problems
 - __Consumer / Producer__
 - __Reader / Writer__
 - __No Starve Mutex__
 
 
#### Java
- Thread Safety
- Java Memory Model / Sharing Objects
- Immutability

Synchronize
- wait, sleep, notify, join, yield
- shutdown hook
- synchronized
- volatile
  - happens before
- locks
  - fair
  - contention/splitting/striping
  - conditions
- ThreadLocal


Thread
 - properties (priority, name, state, identifier, group)
 - daemon
 - local/shared variables
 
 
Thread Use
- starting
- stopping
 - cancellation
 - interruption


Collections
 - BlockingQueue

Executors
```java
// constructor
```
 - Runnable
 - Callable
 - Future
 - stopping