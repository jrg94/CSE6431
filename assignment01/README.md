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

```
Procedure reader
  P(reader_mutex)
  if readers = 0 then     
    readers = readers + 1
    P(writer_mutex)
  else     
    readers = readers + 1
  V(reader_mutex)

  <read file>

  P(reader_mutex)
  readers = readers - 1  
  if readers == 0 then V(writer_mutex)
  V(reader_mutex)

Procedure writer
  P(sr_mutex)
  P(writer_mutex)

  <write file>

  V(writer_mutex)
  V(sr_mutex)
```

## Problem 2

> Implement a solution to the readers/writers problem using monitors which
> execute the requests in FCFS manner. If there are consecutive readers, they
> should be able to access the file concurrently.

```
Procedure startRead
  begin
    readers = readers + 1;
  end

Procedure endRead
  begin
    readers = readers - 1;
    if (readers == 0) then writer.signal;
  end

Procedure writer
  begin
    if (readers > 0) then writer.wait;
    <write file>
  end
```

## Problem 3

> Write a semaphore-based solution to the reader–writers problem that works as
> follows: If readers and writers are both waiting, then it alternates between
> readers and  writers. Otherwise, it processes them normally, i.e., readers
> concurrently and writers serially.  

```
```

## Problem 4

> Write a monitor-based solution to the above problem.

```
```

## Problem 5

> A file is to be shared among different processes, each of which has a unique
> number. The file can be accessed simultaneously by several processes, subject
> to the following constraint: The sum of all unique numbers associated with all   
> the processes concurrently accessing the file must be less than n. Write a
> monitor to coordinate accesses to the file.

```
```

## Problem 6

> Using Java support for multithreading (Synchronized, wait, and notifyall),  
> write a solution to the producer-consumer problem with a buffer of length N.  
> Submit your solution on paper (i.e. do not worry about exact syntax or debugging).

The following solution demonstrates just the shared memory portion of the
producers/consumers problem. In addition to this class, you'd need to create
producer and consumer classes which call the produce and consume methods
in some way.

```java
/**
 * A shared memory space class with functions for consumption and production.
 *
 * @author Jeremy Grifski
 */
public class Buffer {
  private ArrayList<Integer> buffer;
  private maxSize;

  public Buffer(int maxSize) {
    this.buffer = new ArrayList<Integer>();
    this.maxSize = maxSize;
  }

  /**
   * A production method which generates a random value between 0 and 1000
   * and places it at the end of shared memory.
   */
  public synchronized void produce() {
    while (buffer.size() == maxSize) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("Producer awoken!");
      }
    }
    this.buffer.add((int)(Math.random() * 1000));
    notifyAll();
  }

  /**
   * A consumption method which consumes the first value from the buffer.
   */
  public synchronized int consume() {
    while(buffer.isEmpty()) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("Consumer awoken!");
      }
    }
    int value = this.buffer.remove(0);
    notifyAll();
    return value;
  }
}
```

As you can see, this solution leverages all that is asked via synchronized,
wait, and notifyAll. The following code snippets are the class stubs for
the producer and consumer classes:

```java
public class Producer extends Thread {
  private Buffer sharedMemory;

  public Producer(Buffer sharedMemory) {}

  public void run() {
    // Implement production loop
  }
}
```

```java
public class Consumer extends Thread {
  private Buffer sharedMemory;

  public Consumer(Buffer sharedMemory) {}

  public void run() {
    // Implement consumption loop
  }
}
```

## Problem 7

> Using Java support for multithreading (Synchronized, wait, and notifyall),  
> write a solution to the readers-writers problem, with exclusive writer access,  
> concurrent reader access, and reader’s priority. Submit your solution on paper
> (i.e. do not worry about exact syntax or debugging).  

```java
```
