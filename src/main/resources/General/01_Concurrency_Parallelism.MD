## Process
- Task representing execution within the operating system, sharing as much resource as its given

## Thread
- A lightweight process running within a Process, sharing the resources that the Process has

## Primal Processing
- just a single task running

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/general/01_Singletasking.png">

## Parallel
An application splits its tasks up into smaller subtasks which can be processed in parallel, for instance on multiple CPUs at the exact same time
- each thread has its own task
- switching between threads / time-slice

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/general/02_Parallel_Execution.png">

## Concurrent
An application is making progress on multiple tasks seemingly at the same time (concurrently)

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/general/03_Concurrency.png">

## Parallel and Concurrent
Utilizes multiple processors that are splitting and executing tasks concurrently

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/general/04_Parallel_Concurrent.png">

When a CPU switches from executing one thread to executing another, the CPU needs to save the local data, program pointer etc. of the current thread, and load the local data, program pointer etc. of the next thread to execute. This switch is called a __"context switch"__

Both Processes and Threads are independent sequences of execution
