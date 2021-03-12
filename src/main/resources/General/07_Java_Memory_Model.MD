Each Threads has its own memory stack on which it keeps local information

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/general/05_Java_Threads_Stack_Memory.jpg">

Threads can only access their local variables and Shared Objects. They cannot see other Threads' local variables

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/general/06_Java_Threads_Stack_Memory_2.png">


The following diagram illustrates the sketched situation. One thread running on the left CPU copies the shared object into its CPU cache, and changes its count variable to 2. This change is not visible to other threads running on the right CPU, because the update to count has not been flushed back to main memory yet.

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/general/07_Java_Memory_Multiple_CPUs.png">
 - can use volatile
 
When a variable is Shared among Threads, if there isn't proper synchronization Race Conditions might occur

