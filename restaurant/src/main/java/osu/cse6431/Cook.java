package osu.cse6431;

public class Cook extends Thread {

    private Resources resources;

    public Resources getResources() {
      return this.resources;
    }

    public void with(Resources resources) {
        this.resources = resources;
    }

    public synchronized void serve() {
        while (this.getResources().getActiveDiners().size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("Thread crashed");
            }
        }
        this.notifyAll();
    }

    public void run() {
      System.out.println("Cook doing stuff");
    }
}
