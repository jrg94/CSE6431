# Restaurant Project

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
