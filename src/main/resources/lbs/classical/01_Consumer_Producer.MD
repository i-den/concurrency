#### Consumer Producer
```
mutex = semaphore(1)
items = semaphore(0)
spaces = semaphore(buffer.size())
```
Consumer
```
items.wait()
mutex.wait()
    event = buffer.get()
mutex.signal()
spaces.signal()

event.processT()
```
Producer
```
event = waitForEvent()

spaces.wait()
mutex.wait()
    buffer.add(event)
mutex.signal()
items.signal()
```

#### Finite buffer Producer Consumer
```
mutex = semaphore(1)
items = semaphore(0)
spaces = semaphore(buffer.size())
```
Consumer
```
items.wait()
mutex.wait()
    event = buffer.get()
mutex.signal()
spaces.signal()

// process event
```
Producer
```
event = waitForEvent()

spaces.wait()
mutex.wait()
    buffer.add(event)
mutex.notify()
items.signal()
```
