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
        Diner activeDiner = this.getResources().takeOrder();
        return activeDiner;
    }

    /**
     * Cooks a burger when the grill becomes available.
     */
    public synchronized void cookBurger(Diner order, int index) {
        int startTime = this.getResources().getGlobalClock();
        String output = String.format("Cooking burger %d of %d for diner %d", index, order.getBurgerOrderCount(),
                order.getIndex());
        this.getResources().log(output);
        this.machineLoop(startTime, Restaurant.BURGER_COOK_TIME);
    }

    /**
     * Cooks fries when the fryer becomes available.
     */
    public synchronized void cookFries(Diner order, int index) {
        int startTime = this.getResources().getGlobalClock();
        String output = String.format("Cooking fries %d of %d for diner %d", index, order.getFryOrderCount(),
                order.getIndex());
        this.getResources().log(output);
        this.machineLoop(startTime, Restaurant.FRY_COOK_TIME);
    }

    /**
     * Pours soda when the soda machine becomes available.
     */
    public synchronized void pourSoda(Diner order) {
        int startTime = this.getResources().getGlobalClock();
        String output = String.format("Pouring soda for diner %d", order.getIndex());
        this.getResources().log(output);
        this.machineLoop(startTime, Restaurant.SODA_FILL_TIME);
    }

    /**
     * A helper method which runs a busy loop for checking the global clock to
     * see if the machine has finished its job based on some delta.
     * 
     * @param startTime the time when the machine was first in use
     * @param delta the duration of use for that machine
     */
    private void machineLoop(int startTime, int delta) {
        while (this.getResources().getGlobalClock() < startTime + delta) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
            cookBurger(order, i + 1);
        }

        for (int i = 0; i < order.getFryOrderCount(); i++) {
            cookFries(order, i + 1);
        }

        for (int i = 0; i < order.getDrinkOrderCount(); i++) {
            pourSoda(order);
        }

        serveOrder();
    }

    /**
     * A helper method for detecting if there are more diners to cook for.
     * 
     * @return true if there are more diners
     */
    public synchronized boolean hasMoreDiners() {
        return this.getResources().getServedDinerCount() != this.getResources().getTotalDinerCount();
    }

    /**
     * Runs the thread.
     */
    @Override
    public void run() {
        while (hasMoreDiners()) {
            Diner order = getOrder();
            if (order != null) {
                completeOrder(order);
            }
        }
    }
}
