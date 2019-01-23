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
