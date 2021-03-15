## Starting
 - inherit
 - use Runnable
 - use Runnable as lambda
```java
Thread t = new Thread(() -> print("Hello"));
t.start();
```

Waiting for a Thread to finish before starting another operation
```java
try {
    t.join();
} catch(InterruptedException ie) {
    // handle interruption
    Thread.currentThread().interrupt();
}
```
## Stopping
```java

class MyRunnable implements Runnable {

    private boolean running = true;

    @Override
    public void run() {
        while (running) {
            System.out.println("Running " + Thread.currentThread().getName());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }
}
```
### Cancellation, Interruption
Java does not provide any mechanism for safely forcing a thread to stop
what it is doing. 

Instead, it provides interruption, a cooperative mechanism that
lets one thread ask another to stop what it is doing

An activity is cancellable if external code can move it to completion before its
normal completion

There is nothing in the API or language specification that ties interruption
to any specific cancellation semantics, but in practice, using interruption
for anything but cancellation is fragile and difficult to sustain in larger
applications

Calling interrupt does not necessarily stop the target thread from doing
what it is doing; it merely delivers the message that interruption has been
requested

A task needn’t necessarily drop everything when it detects an interruption
request—it can choose to postpone it until a more opportune time by remembering
that it was interrupted, finishing the task it was performing, and then throwing
InterruptedException or otherwise indicating interruption

Because each thread has its own interruption policy, you should not interrupt
a thread unless you know what interruption means to that thread.

