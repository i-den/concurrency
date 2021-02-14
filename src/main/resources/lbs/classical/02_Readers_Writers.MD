#### Readers and Writers
```
readers = 0
mutex = semaphore(1)
roomEmpty = semaphore(1)
```
Writers
```
roomEmpty.wait()
    # critical section
roomEmpty.signal()
```
Readers
```
readLightswitch = lightswitch()
```
```
readLightswitch.lock(roomEmpty)
    # critical section for readers
readLightswitch.unlock(roomEmpty)
```
without Lightswitch
```
mutex.wait()
    readers++;
    if readers == 1:
        roomEmpty.wait() // first locks
mutex.signal()

# critical section for readers

mutex.wait()
    readers--;
    if readers == 0:
        roomEmpty.signal()
mutex.signal()
```

#### No-starve Readers Writers
```
readSwitch = lightswitch()
roomEmpty = semaphore(1)
turnstile = semaphore(1)
```
Readers
```
turnstile.wait()
turnstile.notify()

readSwitch.lock(roomEmpty)
    # critical section
readSwitch.unlock(roomEmpty)
```
Writers
```
turnstile.wait()
    roomEmpty.wait()
    # critical section
turnstile.notify()
roomEmpty.notify()
```

#### Writer Priority
```
readSwitch = lightswitch()
writeSwitch = lightswitch()
noReaders = semaphore(1)
noWriters = semaphore(1)
```
Readers
```
noReaders.wait()
    readSwitch.lock(noWriters)
noReaders.notify()
   
# critical section

readSwitch.unlock(noWriters)
```
Writers
```
writeSwitch.lock(noReaders)
    noWriters.wait()
        # critical section
    noWriters.notify()
writeSwitch.unlock(noReaders)
```
