Weak Semaphore

Property 3: if there are threads waiting on a semaphore when a
thread executes signal, then one of the waiting threads has to be
woken.


Strong semaphore

Property 4: if a thread is waiting at a semaphore, then the number
of threads that will be woken before it is bounded.

#### No Starve Mutex
It uses two
turnstiles to create two waiting rooms before the critical section. The mechanism
works in two phases. During the first phase, the first turnstile is open and the
second is closed, so threads accumulate in the second room. During the second
phase, the first turnstile is closed, so no new threads can enter, and the second
is open, so the existing threads can get to the critical section.
Although there may be an arbitrary number of threads in the waiting room,
each one is guaranteed to enter the critical section before any future arrivals.

```
room1 = 0  // num of threads in first room
room2 = 0
mutex = semaphore(1)
t1 = semaphore(1)
t2 = semaphore(0)
```
```
mutex.wait()
    room1++
mutex.notify()

t1.wait() { // first section
    room2++

    mutex.wait()
    room1--

    if room1 == 0:
        mutex.signal()
        t2.signal()
    else:
        mutex.signal()
        t1.signal()
}

t2.wait() { // second section
    room2--
    
    if room2 == 0:
        t1.notify()
    else:
    t2.signal()
}
```
The moral of this story is that with weak semaphores, it is very difficult to
prevent starvation, even for the simplest synchronization problems. In the rest
of this book, when we discuss starvation, we will assume strong semaphores

