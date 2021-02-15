#### Semaphore
 - Integer, after initialization you can only increment/decrement by 1
 - When a Thread decrements and the result is negative it blocks until another Thread increments it
 - If there are other Threads waiting when the Semaphore is incremented one of the Threads get unblocked
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
 - a Semaphore with a value of 1
 - only a single Thread might hold it
```
mutex = semaphore(1)

mutex.wait()
    # critical section
mutex.notify()
```
#### Multiplex 
```
multiplex = semaphore(n)

multiplex.wait()
    # critical section
multiplex.notify()
```
#### Barrier
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
```
barrier.wait()
barrier.signal()
```

#### Lightswitch
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
