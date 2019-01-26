public class SharedFile {

  private int readCount;
  private int writeCount;

  public SharedFile() {
    this.readCount = 0;
    this.writeCount = 0;
  }

  public synchronized void startRead() {
    while (writeCount > 0) {
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
    readCount--;
    if (this.readCount == 0) {
      notifyAll();
    }
  }

  public synchronized void startWrite() {
    while (readCount > 0 || writeCount > 0) {
      try {
        wait();
      } catch (InterruptedException e) {
        System.out.println("Writer wants to write!")
      }
    }
    writeCount++;
  }

  public synchronized void endWrite() {
    writeCount--;
    notifyAll();
  }

}
