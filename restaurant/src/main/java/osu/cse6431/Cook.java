package osu.cse6431;

public class Cook extends Thread {

    private Resources resources;

    public Resources getResources() {
      return this.resources;
    }

    public void with(Resources resources) {
        this.resources = resources;
    }

    public synchronized Diner getOrder() {
        while (this.getResources().getActiveDiners().size() == 0) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("Thread crashed");
            }
        }
        Diner activeDiner = this.getResources().takeOrder();
        this.notifyAll();
        return activeDiner;
    }

    public synchronized void cookBurger(Diner order) {
        if (order.getBurgerOrderCount() > 0) {
            while (!this.getResources().isGrillAvailable()) {
                /// wait
            }
        }
    }

    public void completeOrder(Diner order) {
        cookBurger(order);
    }

    public void run() {
      System.out.println("Cook doing stuff");
      Diner order = getOrder();
      completeOrder(order);
    }
}
