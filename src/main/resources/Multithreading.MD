Both Processes and Threads are independent sequences of execution

#### Process
- instance of program execution

#### Thread
- light-weight process
- a unit of execution withing a given process

Processing time for a single core is shared among processes and threads - time slicing

Parallel - multiple cores each running a thread continuously

#### Thread States
 - new
   - when instantiated
   - until .start() is called
 - runnable
   - after we have started the thread
   - thread is executing a task in this state
 - waiting
   - wait() and sleep()
 - dead
   - after task is finished
   
#### Daemon or Worker threads
#### Daemon
 - low priority thread that runs in the background
 - terminated by JVM when all other worker threads are terminated
 
 