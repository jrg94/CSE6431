public class Buffer {
  private ArrayList<Integer> buffer;
  private boolean ready;

  public Buffer() {
    this.buffer = new ArrayList<Integer>();
    this.ready = false;
  }

  /**
   * A production method which generates a random value between 0 and 1000
   * and places it at the end of shared memory.
   */
  public synchronized void produce() {
    while (ready) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("Producer awoken!");
      }
    }
    this.buffer.add((int)(Math.random() * 1000));
    this.ready = true;
    notifyAll();
  }

  public synchronized int consume() {
    return 0;
  }
}
