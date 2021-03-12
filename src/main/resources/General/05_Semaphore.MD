## Semaphore
Like an Integer:
  - can create with any number (mutex, multiplex)
  - once created can only __Increment__/__Decrement__
  - when a Thread Decrements and gets a negative value it blocks until another Thread Increments
  - when a Thread Increments and there are other Threads waiting one of those Threads gets unblocked
  
Weak Semaphores
 - there is no order at which Threads are given access after incrementing the Semaphore

Strong Semaphores
 - there is order in which the Threads are given access when waiting for a Semaphore to be incremented

```
 pass = semaphore(1)
 
 pass.increment()
 pass.decrement()
 
 pass.wait()
 pass.notify()
 
 // And V and P were the original names proposed by Dijkstra,
 // who wisely realized that a meaningless name is better than a misleading name
 pass.V()
 pass.P() 
 ```
 
#### Rendezvous
__Ensuring that one action happens before another__
 - action_A1 happens before action_B2
 - action_B1 happens before action_A2
```
a = semaphore(1)
b = semaphore(1)
```
Thread 1
```
action_A1
a.notify()
b.wait()
action_A2
```
Thread 2
```
action_B1
b.notify()
a.wait()
action_B2
```

#### Mutex
Used to gain Mutual Exclusive access to a given Critical Section
 - a Semaphore with a value of 1
 - only a single Thread might hold it
```
mutex = semaphore(1)

mutex.wait()
    # critical section
mutex.notify()
```
#### Multiplex 
Used to give access to a Critical Section on N Threads at a time
```
multiplex = semaphore(n)

multiplex.wait()
    # critical section
multiplex.notify()
```
#### Barrier
Used to make N Threads to gather before executing a Critical Section
```
n = threadNum
count = 0;
mutex = semaphore(1)
barrier = semaphore(0)
```
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
The Barrier is usually a Mutex. When unlocking it it will allow only one Thread to pass, then it will block again.
This is why a Turnstile is used to make the passing Thread unlock the Barrier for the other Threads after it
```
barrier.wait()
barrier.signal()
```

#### Lightswitch
Allowing X Threads to gain access and then unlocking only after ALL of them leave
```
int visitors = 0;
lightswitch = semaphore(1)

mutex.wait()
    visitors++
    if visitors = 1:
        lightswitch.wait() // first one locks
mutex.signal()

# critical section

mutex.wait()
    visitors--
    if visitors = 0:
        lightswitch.signal() // last one unlocks
mutex.signal()
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

 