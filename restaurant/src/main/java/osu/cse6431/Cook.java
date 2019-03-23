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
    public synchronized void cookBurger() {
        System.out.println("Cooking burger");
    }

    /**
     * Cooks fries when the fryer becomes available.
     */
    public synchronized void cookFries(Diner order) {
        String output = String.format("Cooking fries for Diner #%d", order.getIndex());
        System.out.println(output);
    }

    /**
     * Pours soda when the soda machine becomes available.
     */
    public synchronized void pourSoda() {
        System.out.println("Pouring soda");
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
            cookFries(order);
        }

        for (int i = 0; i < order.getDrinkOrderCount(); i++) {
            pourSoda();
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
