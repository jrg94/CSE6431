package osu.cse6431;

/**
 * A cook simulator.
 * 
 * @author Jeremy Grifski
 */
public class Cook extends Thread {

    private Resources resources;
    private int index;

    /**
     * A cook constructor.
     * 
     * @param index the id of the cook
     */
    public Cook(int index) {
        this.setIndex(index);
    }

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
    public Diner getOrder() {
        Diner activeDiner = this.getResources().takeOrder();
        return activeDiner;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    private void setIndex(int index) {
        this.index = index;
    }

    /**
     * Cooks a burger when the grill becomes available.
     */
    public synchronized void cookBurger(Diner order, int index) {
        int startTime = this.getResources().getGlobalClock();
        String output = String.format("Cooking burger %d of %d for diner %d", index, order.getBurgerOrderCount(),
                order.getIndex());
        this.getResources().log(output);
        this.getResources().machineLoop(startTime, Restaurant.BURGER_COOK_TIME);
    }

    /**
     * Cooks fries when the fryer becomes available.
     */
    public synchronized void cookFries(Diner order, int index) {
        int startTime = this.getResources().getGlobalClock();
        String output = String.format("Cooking fries %d of %d for diner %d", index, order.getFryOrderCount(),
                order.getIndex());
        this.getResources().log(output);
        this.getResources().machineLoop(startTime, Restaurant.FRY_COOK_TIME);
    }

    /**
     * Pours soda when the soda machine becomes available.
     */
    public synchronized void pourSoda(Diner order) {
        int startTime = this.getResources().getGlobalClock();
        String output = String.format("Pouring soda for diner %d", order.getIndex());
        this.getResources().log(output);
        this.getResources().machineLoop(startTime, Restaurant.SODA_FILL_TIME);
    }

    /**
     * Serves the order which frees up a table.
     */
    public void serveOrder(Diner order) {
        order.setHasFood(true);
    }

    /**
     * Completes an order asynchronously.
     * 
     * @param order a diner with an order
     */
    public void completeOrder(Diner order) {
        String msg = String.format("Cook %d receives order from diner %d", this.getIndex(), order.getIndex());
        this.getResources().log(msg);

        for (int i = 0; i < order.getBurgerOrderCount(); i++) {
            cookBurger(order, i + 1);
        }

        for (int i = 0; i < order.getFryOrderCount(); i++) {
            cookFries(order, i + 1);
        }

        for (int i = 0; i < order.getDrinkOrderCount(); i++) {
            pourSoda(order);
        }

        serveOrder(order);
    }

    /**
     * Runs the thread.
     */
    @Override
    public void run() {
        while (this.getResources().hasMoreDiners()) {
            Diner order = getOrder();
            if (order != null) {
                completeOrder(order);
            }
        }
    }
}
