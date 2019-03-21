package osu.cse6431;

public class Cook extends Thread {

    private Resources resources;

    public void with(Resources resources) {
        this.resources = resources;
    }

    public void run() {
      System.out.println("Cook doing stuff");
    }
}
