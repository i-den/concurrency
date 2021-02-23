<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/01_Concurrency_Multithreading/01_Singletasking.png">
 - just a single task running

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/01_Concurrency_Multithreading/02_Multitasking.png">
 - each thread has its own task
 - switching between threads / time-slice
 
<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/01_Concurrency_Multithreading/03_Parallel_Multithread.png">
When a CPU switches from executing one thread to executing another, the CPU needs to save the local data, program pointer etc. of the current thread, and load the local data, program pointer etc. of the next thread to execute. This switch is called a "context switch"

Both Processes and Threads are independent sequences of execution

#### Process
- instance of program execution

#### Thread
- light-weight process
- a unit of execution withing a given process

The process is used to execute something, a program. To break down the program into several execution segments something lightweight was needed - the thread.

The thread is a mean of saying I'm breaking down my process to those core parts, and the operating system then takes responsibility into scheduling their execution

Processing time for a single core is shared among processes and threads - time slicing

Parallel - multiple cores each running a thread continuously
