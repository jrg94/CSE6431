# Restaurant Project

Welcome to the restaurant project. To learn more, browse the
sections below.

## Execution

## Input

Input is expected as an input stream on stdin. Input is expected
to be in the following format:

```
# of diners (n)
# of tables
# of cooks
n lines of diners and their orders
```

Each diner line should be in the following format:

```
time burgerCount fryCount sodaCount
```

In other words, the program expects each item to be
separated by a single space. The program cannot handle
malformed input. Also, all values are expected as integers.

## Output

All output from the restaurant program is sent to stdout.
The following snippet is a sample log of the simulation:

```
T-0: Diner 0 is seated @ table 1
T-0: Cook 0 receives order from diner 0
T-0: Cooking burger 1 of 2 for diner 0
T-5: Cooking burger 2 of 2 for diner 0
T-10: Cooking fries 1 of 1 for diner 0
T-10: Diner 1 is seated @ table 2
T-13: Cook 0 receives order from diner 1
T-13: Cooking burger 1 of 2 for diner 1
T-13: Diner 0 begins eating
T-18: Cooking burger 2 of 2 for diner 1
T-20: Diner 2 is seated @ table 3
T-23: Cooking fries 1 of 1 for diner 1
T-26: Pouring soda for diner 1
T-27: Cook 0 receives order from diner 2
T-27: Diner 1 begins eating
T-27: Cooking burger 1 of 1 for diner 2
T-32: Cooking fries 1 of 1 for diner 2
T-35: Pouring soda for diner 2
T-36: Diner 2 begins eating
T-43: Diner 0 leaves diner
T-57: Diner 1 leaves diner
T-66: Diner 2 leaves diner
T-66: The last diner left
```

In this example, you can see the timestamp followed by a colon
followed by a message. The message conveys as much information 
as possible about what happened at that instance in time.

For example, when diners arrive, the log will indicate the diner's
ID (an index) and what table they were seated at. Likewise, cooks
are also indicated by an ID (an index), and their interaction with
the diners and their orders can be found in the log.

## Description

The program works by breaking functionality into four classes: Restaurant,
Cook, Diner, and Resources. 

The Restaurant class serves as the main thread which launches the simulation. 
It is also responsible for maintaining the global clock.

The Diner class serves as a diner thread. Each diner thread waits until the
global clock matches their arrival time before adding themselves to the table
queue. If there are any waiting cooks, they're popped of the queue and seated.
Seated diners then wait for their order to be filled by periodically checking
if they have food. If they do, they begin the process of eating which is
another process that periodically checks the global clock to see if 30 minutes
have passed. If 30 minutes have passed, the diner then leaves. 

The Cook class serves as a cook thread. Each cook thread waits for a diner to
come across the table queue. Once one becomes available, they seat the diner,
take their order, and begin preparation. Preparation begins by polling the
grill for as many burgers as need to be cooked. Then, the same is done for 
the fryer as well as the soda machine, in that order. After completing the
order, the cook indicates to the diner that their food is ready before
taking another order.

Finally, the Resources class is responsible for storing shared information
between the cooks and diners like the global clock, the total table count,
the taken table count, the total served count, and the total number of
diners, and a list of active diners.

## Opportunities for Improvements

The biggest area for improvement is kitchen resource usage among cooks.
Currently, the kitchen is setup such that cooks try to prepare burgers, fries,
and soda in that order. Because of this order, it's possible that three cooks 
could be waiting for the grill while both the fryer and the soda machine are 
open. The cooks are not smart enough to try to acquire any of the three machines. 
Introducing locks may allow cooks to accomplish better performance. Also, due 
to the nature of Java synchronization, it's possible for cooks to become starved 
while waiting for a machine. 
