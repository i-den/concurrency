### Little Book of Semaphores

#### What is Synchronization
Relationship between events - any number and any event (before, during, after) Computer can be Parallel - use more than 1 Processor A single Processor can run multiple Threads We know the order of operations in a single Processor/Thread, but not between multiples

#### Values - local, shared, unsynchronized threads
Local - not shared among threads. Less issues, threads don't interact much Shared - one thread reads from another thread. If they're unsynchronized you cannot guarantee what the reading thread will read. One way - reader should not read before writer writes.

#### Sequential and Concurrent events
Sequential - happening in an order Concurrent - two events are concurrent if we cannot tell by looking at the program which will happen first.

#### Semaphore

#### Non-determinism

#### Mutex

#### Multiplex 

#### Barrier
```
rendezvous

mutex.wait()
    count = count + 1
mutex.signal()

if count == n:
    barrier.signal()

barrier.wait()
barrier.signal()

critical point
```

#### Turnstile
```
barrier.wait()
barrier.signal()
```
#### Queue
leaders
```
mutex . wait ()
    if followers > 0:
        followers --
        followerQueue . signal ()
    else :
        leaders ++
        mutex . signal ()
        leaderQueue . wait ()

dance ()
rendezvous . wait ()
mutex . signal ()
```
followers
```
mutex . wait ()
    if leaders > 0:
        leaders --
        leaderQueue . signal ()
    else :
        followers ++
        mutex . signal ()
        followerQueue . wait ()

dance ()
rendezvous . signal ()
```

### Problems
#### Consumer Producer
```
mutex = Semaphore (1)
items = Semaphore (0)
spaces = Semaphore ( buffer . size ())
```
Consumer
```
items . wait ()
mutex . wait ()
    event = buffer . get ()
mutex . signal ()
spaces . signal ()

event . process ()
```
Producer
```
event = waitForEvent ()

spaces . wait ()
mutex . wait ()
    buffer . add ( event )
mutex . signal ()
items . signal ()
```