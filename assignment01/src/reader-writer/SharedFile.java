public class SharedFile {

  private boolean busy;
  private int readCount;

  public SharedFile() {
    this.busy = false;
    this.readCount = 0;
  }

  public synchronized void startRead() {
    while (busy) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("Reader wants to read!");
      }
    }
    readCount++;
    notifyAll();
  }

  public synchronized void endRead() {

  }

  public synchronized void startWrite() {

  }

  public synchronized void endWrite() {

  }

}
