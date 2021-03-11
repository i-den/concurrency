## Atomic
Operation that happens in one step

Either all the operation executes or none of it does

Often an issue with Race Conditions or Compound Operations

## Compare and Swap
Multiple Threads competing to write a change. Only one wins and the others are "told" they can decide on whether they wish to try writing again

The Threads check the initial state of the object they're writing to. Perform work and then see if the initial value is not changed

If it's not - they update it. If it is changed - they decide on whether they wish to perform work and try to update it again

This is called Optimistic Locking - it doesn't block, and the Thread is optimistic it will change the value

Pessimistic Locking is when the Thread blocks, waiting for another do to work on the value

Atomic Operations often use Optimistic Locking 