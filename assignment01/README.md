Jeremy Grifski

February 5th, 2019

CSE 5544

Dr. Feng Qin

# Assignment 01

No solutions turned in after the end of the class that day will receive full
points.

## Problem 1

> Implement a solution with writers’ priority to the readers/writers problem
> using semaphores.

## Problem 2

> Implement a solution to the readers/writers problem using monitors which
> execute the requests in FCFS manner. If there are consecutive readers, they
> should be able to access the file concurrently.

## Problem 3

> Write a semaphore-based solution to the reader–writers problem that works as
> follows: If readers and writers are both waiting, then it alternates between
> readers and  writers. Otherwise, it processes them normally, i.e., readers
> concurrently and writers serially.  

## Problem 4

> Write a monitor-based solution to the above problem.

## Problem 5

> A file is to be shared among different processes, each of which has a unique
> number. The file can be accessed simultaneously by several processes, subject
> to the following constraint: The sum of all unique numbers associated with all   
> the processes concurrently accessing the file must be less than n. Write a
> monitor to coordinate accesses to the file.

## Problem 6

> Using Java support for multithreading (Synchronized, wait, and notifyall),  
> write a solution to the producer-consumer problem with a buffer of length N.  
> Submit your solution on paper (i.e. do not worry about exact syntax or debugging).

## Problem 7

> Using Java support for multithreading (Synchronized, wait, and notifyall),  
> write a solution to the readers-writers problem, with exclusive writer access,  
> concurrent reader access, and reader’s priority. Submit your solution on paper
> (i.e. do not worry about exact syntax or debugging).  
