# Little Book of Semaphores
## Concepts

#### What is Synchronization
Relationship between events - any number and any event (before, during, after) Computer can be Parallel - use more than 1 Processor A single Processor can run multiple Threads We know the order of operations in a single Processor/Thread, but not between multiples

#### Values - local, shared, unsynchronized threads
Local - not shared among threads. Less issues, threads don't interact much Shared - one thread reads from another thread. If they're unsynchronized you cannot guarantee what the reading thread will read. One way - reader should not read before writer writes.

#### Sequential and Concurrent events
Sequential - happening in an order Concurrent - two events are concurrent if we cannot tell by looking at the program which will happen first.

#### Non-determinism
 - Not being able to tell the exact result of an execution
 