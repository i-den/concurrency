## Deadlock
When two or more threads are waiting to obtain a lock that another thread is holding and cannot release

When a process is waiting for a resource held by another process that will not be released

## Livelock
When processes are doing work and its state is constantly changing without making progress

A thread that is doing work but cannot proceed

## Busy Waiting
When a Thread is waiting for an event and consuming CPU time
```java
while(!eventOccured) { // busy waiting
}
```

## Starvation
When multiple threads are waiting for executing a critical section and the entrance to the critical section is not ordered. A thread might wait forever