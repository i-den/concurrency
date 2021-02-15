A barbershop consists of a waiting room with n chairs, and the
barber room containing the barber chair. If there are no customers
to be served, the barber goes to sleep. If a customer enters the
barbershop and all chairs are occupied, then the customer leaves
the shop. If the barber is busy, but chairs are available, then the
customer sits in one of the free chairs. If the barber is asleep, the
customer wakes up the barber. Write a program to coordinate the
barber and the customers


n is the total number of customers that can be in the shop: three in the
waiting room and one in the chair.
customers counts the number of customers in the shop; it is protected by
mutex.
The barber waits on customer until a customer enters the shop, then the
customer waits on barber until the barber signals him to take a seat.
After the haircut, the customer signals customerDone and waits on
barberDone.
```
n = 4 // num of seats
customers = 0

mutex = semaphore(1)
customer = semaphore(0)
barber = semaphore(0)
customerDone = semaphore(0)
barberDone = semaphore(0)
```
Barber
```
customer.wait()
barber.signal()

# critical section // cutHair()

customerDone.wait()
barberDone.signal()
```
Customer
```
mutex.wait()
    if customer == n:
       mutex.signal()
       walk() // exits
    customers++
mutex.signal()

customer.signal()
barber.wait()

# getHaircut()

customerDone.signal()
barberDone.wait()

mutex.wait()
    customers--
mutex.signal()
```
