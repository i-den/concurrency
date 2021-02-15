Our barbershop has three chairs, three barbers, and a waiting
area that can accommodate four customers on a sofa and that has
standing room for additional customers. Fire codes limit the total
number of customers in the shop to 20.
A customer will not enter the shop if it is filled to capacity with
other customers. Once inside, the customer takes a seat on the sofa
or stands if the sofa is filled. When a barber is free, the customer
that has been on the sofa the longest is served and, if there are any
standing customers, the one that has been in the shop the longest
takes a seat on the sofa. When a customer’s haircut is finished,
any barber can accept payment, but because there is only one cash
register, payment is accepted for one customer at a time. The barbers
divide their time among cutting hair, accepting payment, and
sleeping in their chair waiting for a customer.

```
n = 20
customers = 0

mutex = semaphore(1)
sofa = semaphore(4)

customer1 = semaphore(0)
customer2 = semaphore(0)

barber = semaphore(0)
payment = semaphore(0)
receipt = semaphore(0)

queue1 = []
queue2 = []
```
Barber
```
customer1.wait()
mutex.wait()
    sem = queue1.pop()
    sem.signal()
    sem.wait()
mutex.signal()
sem.signal()

customer2.wait()
mutex.wait()
    sem = queue2.pop()
mutex.signal()
sem.signal()

barber.signal()

# cutHair()

payment.wait()

# acceptPayment()

receipt.signal()
```
Customer
```
local sem1 = semaphore(0)
local sem2 = semaphore(0)

mutex.wait()
    if customers == n:
        mutex.signal() 
        walk() // leave
    customers++
    queue1.queue(sem1)
mutex.signal()

# enterShop()

customer1.signal()
sem1.wait()

sofa.wait()
    # sitOnSofa()
    sem1.signal()
    
    mutex.wait()
        queue2.queue(sem2)
    mutex.signal()
    
    customer2.signal()
    sem2.wait()
sofa.signal()

# sitInBarberChair()

# pay()
payment.signal()
receipt.wait()

mutex.wait()
    customers--
mutex.signal()    
```