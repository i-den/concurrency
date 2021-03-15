## Wait Notify NotifyAll
 - wait blocks a Thread
   - Simply put, when we call wait() – this forces the current thread to wait until some other thread invokes notify() or notifyAll() on the same object
   - Prior to calling the wait() method, a thread should always test the condition while holding the synchronization lock. Upon returning from the wait() method, the thread should always retest the condition to determine if it should wait again. This is because another thread can also test the condition and determine that a wait is not necessary — processing the valid data that was set by the notification thread.
 - notify unblocks a Thread
   - For all threads waiting on this object's monitor (by using any one of the wait() method), the method notify() notifies any one of them to wake up arbitrarily. The choice of exactly which thread to wake is non-deterministic and depends upon the implementation.
     
     Since notify() wakes up a single random thread it can be used to implement mutually exclusive locking where threads are doing similar tasks, but in most cases, it would be more viable to implement notifyAll().
 - notifyAll unblocks all Threads but only one may continue
   - This method simply wakes all threads that are waiting on this object's monitor.
   -  Why would you want to wake up all of the threads if only one is going to execute at all?
     There are a few reasons. For example, there might be more than one condition to wait for. Since we cannot control which thread gets the notification, it is entirely possible that a notification wakes up a thread that is waiting for an entirely different condition.
     By waking up all the threads, we can design the program so that the threads decide among themselves which thread should execute next. Another option could be when producers generate data that can satisfy more than one consumer. Since it may be difficult to determine how many consumers can be satisfied with the notification, an option is to notify them all, allowing the consumers to sort it out among themselves 
     The awakened threads will complete in the usual manner – like any other thread.
     But before we allow their execution to continue, always define a quick check for the condition required to proceed with the thread – because there may be some situations where the thread got woken up without receiving a notification
 
To call wait or notify a Thread must own the intrinsic lock - called within a synchronized block

join waits for a Thread to finish its execution

yield() basically means that the thread is not doing anything particularly important and if any other threads or processes need to be run, they should run. Otherwise, the current thread will continue to run.
