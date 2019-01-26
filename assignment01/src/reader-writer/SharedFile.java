/**
 * A shared file with functionality for reading and writing.
 *
 * @author Jeremy Grifski
 */
public class SharedFile {

  private int readCount;
  private int writeCount;

  public SharedFile() {
    this.readCount = 0;
    this.writeCount = 0;
  }

  /**
   * A start read method which kicks off reading iff there are no writers.
   */
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

  /**
   * An end read method which notifies everyone when there are no readers left.
   */
  public synchronized void endRead() {
    readCount--;
    if (this.readCount == 0) {
      notifyAll();
    }
  }

  /**
   * A start write method which kicks off writing if there are no other readers
   * or writers.
   */
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

  /**
   * An end write method whichs notifies everyone when writing is done.
   */
  public synchronized void endWrite() {
    writeCount--;
    notifyAll();
  }

}
