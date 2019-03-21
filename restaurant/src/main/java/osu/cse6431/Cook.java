package osu.cse6431;

/**
 * A cook simulator.
 * 
 * @author Jeremy Grifski
 */
public class Cook extends Thread {

    private Resources resources;

    /**
     * @return the shared resources
     */
    public Resources getResources() {
        return this.resources;
    }

    /**
     * An production method that allows you to hook in resources on the fly.
     * 
     * @param resources the shared resources object
     * @return itself
     */
    public Cook with(Resources resources) {
        this.resources = resources;
        return this;
    }

    /**
     * Retrieves an order when one becomes available.
     * 
     * @return the diner whose order we're taking
     */
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

    /**
     * Cooks a burger when the grill becomes available.
     */
    public synchronized void cookBurger() {
        while (!this.getResources().isGrillAvailable()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("Thread crashed while cooking burger.");
            }
        }
        this.notifyAll();
    }

    /**
     * Cooks fries when the fryer becomes available.
     */
    public synchronized void cookFries() {
        while (!this.getResources().isFryerAvailable()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.err.println("Thread crashed while cooking fries");
            }
        }
        this.notifyAll();
    }

    /**
     * Pours soda when the soda machine becomes available.
     */
    public synchronized void pourSoda() {
        while (!this.getResources().isSodaMachineAvailable()) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                System.out.println("Thread crashed while pouring soda");
            }
        }
        this.notifyAll();
    }

    /**
     * Serves the order which frees up a table.
     */
    public synchronized void serveOrder() {
        this.getResources().freeTable();
    }

    /**
     * Completes an order asynchronously.
     * 
     * @param order a diner with an order
     */
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

        serveOrder();
    }

    /**
     * Runs the thread.
     */
    @Override
    public void run() {
        System.out.println("Cook doing stuff");
        Diner order = getOrder();
        completeOrder(order);
    }
}
