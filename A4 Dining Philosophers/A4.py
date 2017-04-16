import threading
import random
import time
from pymongo import MongoClient
 
class Philosopher(threading.Thread):
 
    running = True
    connection = MongoClient("127.0.0.1",27017)

    @staticmethod
    def sendToMongo(index):
        
        db = Philosopher.connection.test.dini
        post={"time":time.ctime(),"philo":index}
        db.insert_one(post)
 
    def __init__(self, i, xname, forkOnLeft, forkOnRight):
        threading.Thread.__init__(self)
        self.name = xname
        self.index=i
        self.forkOnLeft = forkOnLeft
        self.forkOnRight = forkOnRight
 
    def run(self):
        while(self.running):
            time.sleep( random.uniform(3,13))
            print('%s is hungry.' % self.name)
            self.dine()
 
    def dine(self):
        fork1, fork2 = self.forkOnLeft, self.forkOnRight
 
        while self.running:
            fork1.acquire(True)
            locked = fork2.acquire(False)
            if locked: break
            fork1.release()
            print('%s swaps forks' % self.name)
            fork1, fork2 = fork2, fork1
        else:
            return
 
        self.dining()
        fork2.release()
        fork1.release()
 
    def dining(self):			
        print ('%s starts eating '% self.name)
        Philosopher.sendToMongo(self.index)
        time.sleep(random.uniform(1,10))
        print ('%s finishes eating and leaves to think.' % self.name)
        
def DiningPhilosophers():
    forks = [threading.Lock() for n in range(5)]
    philosopherNames = ('Aristotle','Kant','Buddha','Marx', 'Russel')
 
    philosophers= [Philosopher(i, philosopherNames[i], forks[i%5], forks[(i+1)%5]) \
            for i in range(5)]
 
    random.seed(507129)
    Philosopher.running = True
    for p in philosophers: p.start()
    time.sleep(100)
    Philosopher.running = False
    print ("Now we're finishing.")

DiningPhilosophers()

'''OUTPUT
[akmyths@localhost A4]$ python A4.py 
Russel is hungry.
Russel starts eating 
Aristotle is hungry.
Marx is hungry.
Marx swaps forks
Kant is hungry.
Kant starts eating 
Buddha is hungry.
Russel finishes eating and leaves to think.
Aristotle swaps forks
Marx starts eating 
Kant finishes eating and leaves to think.
Buddha swaps forks
Aristotle starts eating 
Aristotle finishes eating and leaves to think.
Marx finishes eating and leaves to think.
Buddha starts eating 
Russel is hungry.
Russel starts eating 
Kant is hungry.
Kant swaps forks
Aristotle is hungry.
Russel finishes eating and leaves to think.
Aristotle starts eating 
Marx is hungry.
Buddha finishes eating and leaves to think.
Kant swaps forks
Marx starts eating 
Russel is hungry.
Marx finishes eating and leaves to think.
Russel swaps forks
Aristotle finishes eating and leaves to think.
Russel starts eating 
 Kant starts eating 
Russel finishes eating and leaves to think.
Kant finishes eating and leaves to think.
Buddha is hungry.
Buddha starts eating 
Marx is hungry.
Kant is hungry.
Kant swaps forks
Aristotle is hungry.
Aristotle starts eating 
Buddha finishes eating and leaves to think.
Marx starts eating 
Kant swaps forks
Marx finishes eating and leaves to think.
Russel is hungry.
Russel swaps forks
Buddha is hungry.
Buddha starts eating 
Marx is hungry.
Aristotle finishes eating and leaves to think.
Kant swaps forks
Russel starts eating 
Buddha finishes eating and leaves to think.
Kant starts eating 
Marx swaps forks
Aristotle is hungry.
Russel finishes eating and leaves to think.
Marx starts eating 
 Aristotle swaps forks
Buddha is hungry.
Kant finishes eating and leaves to think.
Buddha swaps forks
Aristotle starts eating 
Aristotle finishes eating and leaves to think.
Marx finishes eating and leaves to think.
Buddha starts eating 
Aristotle is hungry.
Aristotle starts eating 
Russel is hungry.
Russel swaps forks
Kant is hungry.
Marx is hungry.
Buddha finishes eating and leaves to think.
Marx starts eating 
Aristotle finishes eating and leaves to think.
Russel swaps forks
 Kant starts eating 
Kant finishes eating and leaves to think.
Marx finishes eating and leaves to think.
Russel starts eating 
Aristotle is hungry.
Kant is hungry.
Kant starts eating 
Russel finishes eating and leaves to think.
Aristotle swaps forks
Buddha is hungry.
Russel is hungry.
Russel starts eating 
Marx is hungry.
Marx swaps forks
Kant finishes eating and leaves to think.
Aristotle swaps forks
Buddha starts eating 
Russel finishes eating and leaves to think.
Marx swaps forks
Aristotle starts eating 
Buddha finishes eating and leaves to think.
Marx starts eating 
Aristotle finishes eating and leaves to think.
Now we're finishing.
Kant is hungry.
Marx finishes eating and leaves to think.
Aristotle is hungry.
Buddha is hungry.
Russel is hungry.
[akmyths@localhost A4]$ 

DB output

> use test
switched to db test
> show databases
local  0.000GB
test   0.000GB
> show collections
dini
> db.dini.find()
{ "_id" : ObjectId("58ddfa8bead0194fd04ee3d8"), "philo" : 4, "time" : "Fri Mar 31 12:13:23 2017" }
{ "_id" : ObjectId("58ddfa90ead0194fd04ee3d9"), "philo" : 1, "time" : "Fri Mar 31 12:13:28 2017" }
{ "_id" : ObjectId("58ddfa91ead0194fd04ee3da"), "philo" : 3, "time" : "Fri Mar 31 12:13:29 2017" }
{ "_id" : ObjectId("58ddfa92ead0194fd04ee3db"), "philo" : 0, "time" : "Fri Mar 31 12:13:30 2017" }
{ "_id" : ObjectId("58ddfa97ead0194fd04ee3dc"), "philo" : 2, "time" : "Fri Mar 31 12:13:35 2017" }
{ "_id" : ObjectId("58ddfa9bead0194fd04ee3dd"), "philo" : 4, "time" : "Fri Mar 31 12:13:39 2017" }
{ "_id" : ObjectId("58ddfa9fead0194fd04ee3de"), "philo" : 0, "time" : "Fri Mar 31 12:13:43 2017" }
{ "_id" : ObjectId("58ddfaa1ead0194fd04ee3df"), "philo" : 3, "time" : "Fri Mar 31 12:13:45 2017" }
{ "_id" : ObjectId("58ddfaa8ead0194fd04ee3e0"), "philo" : 4, "time" : "Fri Mar 31 12:13:52 2017" }
{ "_id" : ObjectId("58ddfaa8ead0194fd04ee3e1"), "philo" : 1, "time" : "Fri Mar 31 12:13:52 2017" }
{ "_id" : ObjectId("58ddfaadead0194fd04ee3e2"), "philo" : 2, "time" : "Fri Mar 31 12:13:57 2017" }
{ "_id" : ObjectId("58ddfab2ead0194fd04ee3e3"), "philo" : 0, "time" : "Fri Mar 31 12:14:02 2017" }
{ "_id" : ObjectId("58ddfab2ead0194fd04ee3e4"), "philo" : 3, "time" : "Fri Mar 31 12:14:02 2017" }
{ "_id" : ObjectId("58ddfab5ead0194fd04ee3e5"), "philo" : 2, "time" : "Fri Mar 31 12:14:05 2017" }
{ "_id" : ObjectId("58ddfab8ead0194fd04ee3e6"), "philo" : 4, "time" : "Fri Mar 31 12:14:08 2017" }
{ "_id" : ObjectId("58ddfabbead0194fd04ee3e7"), "philo" : 1, "time" : "Fri Mar 31 12:14:11 2017" }
{ "_id" : ObjectId("58ddfac2ead0194fd04ee3e8"), "philo" : 3, "time" : "Fri Mar 31 12:14:18 2017" }
{ "_id" : ObjectId("58ddfac4ead0194fd04ee3e9"), "philo" : 0, "time" : "Fri Mar 31 12:14:20 2017" }
{ "_id" : ObjectId("58ddfac7ead0194fd04ee3ea"), "philo" : 2, "time" : "Fri Mar 31 12:14:23 2017" }
{ "_id" : ObjectId("58ddfacaead0194fd04ee3eb"), "philo" : 0, "time" : "Fri Mar 31 12:14:26 2017" }
{ "_id" : ObjectId("58ddfad1ead0194fd04ee3ec"), "philo" : 3, "time" : "Fri Mar 31 12:14:33 2017" }
{ "_id" : ObjectId("58ddfad1ead0194fd04ee3ed"), "philo" : 1, "time" : "Fri Mar 31 12:14:33 2017" }
{ "_id" : ObjectId("58ddfad5ead0194fd04ee3ee"), "philo" : 4, "time" : "Fri Mar 31 12:14:37 2017" }
{ "_id" : ObjectId("58ddfad9ead0194fd04ee3ef"), "philo" : 1, "time" : "Fri Mar 31 12:14:41 2017" }
{ "_id" : ObjectId("58ddfadeead0194fd04ee3f0"), "philo" : 4, "time" : "Fri Mar 31 12:14:46 2017" }
{ "_id" : ObjectId("58ddfae0ead0194fd04ee3f1"), "philo" : 2, "time" : "Fri Mar 31 12:14:48 2017" }
{ "_id" : ObjectId("58ddfae5ead0194fd04ee3f2"), "philo" : 0, "time" : "Fri Mar 31 12:14:53 2017" }
{ "_id" : ObjectId("58ddfae8ead0194fd04ee3f3"), "philo" : 3, "time" : "Fri Mar 31 12:14:56 2017" }
> 
'''
