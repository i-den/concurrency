The Java happens before guarantee is a set of rules that govern how the Java VM and CPU is allowed to reorder instructions for performance gains. The happens before guarantee makes it possible for threads to rely on when a variable value is synchronized to or from main memory, and which other variables have been synchronized at the same time. The Java happens before guarantee are centered around access to __volatile variables__ and variables accessed from within __synchronized__ blocks.

Can re-oder:
```
a = b + c
d = e + f
```

Cannot
```
a = b + c
d = a + e       // d depends on a
```

<img src="https://raw.githubusercontent.com/i-den/concurrency/master/src/main/resources/pictures/jkv/05_Happens_Before/01_Instruction_Reordering.png">


The happens before guarantee ensures that synchronized blocks and blocks with volatile variables will not be reordered as this might change the behavior

More specifically reads/writes from/to volatile variables will not be reordered

All variables in synchronized block are read at the start and flushed at the end from/to the main memory

 