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

    public synchronized void cookBurger() {
        while (!this.getResources().isGrillAvailable()) {
            /// wait
        }
    }

    public synchonized void cookFries() {
        while(!this.getResources().isFryerAvailable()) {
            // wait
        }
    }

    public synchronized void pourSoda() {
        while (!this.getResources().isSodaMachineAvailable()) {
            // wait
        }
    }

    public void completeOrder(Diner order) {
        for (int i = 0; i < order.getBurgerOrderCount(); i++) {
            cookBurger();
        }

        for (int i = 0; i < order.getFryOrderCount(); i++) {
            cookFries();
        }

        for (int i = 0; i < order.getDrinkOrderCount(); i++) {
            pourSoda();
        }
    }

    public void run() {
        System.out.println("Cook doing stuff");
        Diner order = getOrder();
        completeOrder(order);
    }
}
