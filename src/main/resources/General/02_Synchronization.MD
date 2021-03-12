## Synchronization
Controlled Thread access to Shared Resources

#### Critical Section
Section of code that is executed by multiple threads and where the sequence of execution for the threads makes a difference in the result of the concurrent execution

#### Race Condition
When the result of multiple threads executing the Critical Section code differs depending on the order in which they executed it

Two types:
 - Read-Modify-Write
 - Check-then-Act
 
Synchronizing critical sections prevents race conditions
