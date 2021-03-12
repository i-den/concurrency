#### Locks
Reentrant locks, tryLock
  - synchronized - single block, locks - called from multiple places
  - lock() and unlock() provides same visibility and happens before guarantee as synchronized blocks
  - synchronized blocks are always reentrant, locks can not be
  - locks can guarantee fairness, synchronized blocks can't
  
#### Read/Write Locks
Read Access   	If no threads are writing, and no threads have requested write access.

Write Access   	If no threads are reading or writing.


