package osu.cse6431;

/**
 * A diner simulator.
 *
 * @author Jeremy Grifski
 *
 */
public class Diner extends Thread {

    private int arrivalTime; // 0 to 120
    private int burgerOrderCount; // > 1
    private int fryOrderCount; // > 0
    private int drinkOrderCount; // 0 or 1
    private int index;
    private Resources resources;
    private boolean hasFood;

    /**
     * Initializes a diner instance given four parameters.
     *
     * @param arrivalTime the arrival time of the diner
     * @param burgerOrderCount the number of burgers the diner orders
     * @param fryOrderCount the number of fries the diner orders
     * @param drinkOrderCount the number of drinks the diner orders
     */
    public Diner(int arrivalTime, int burgerOrderCount, int fryOrderCount, int drinkOrderCount, int index) {
        this.setArrivalTime(arrivalTime);
        this.setBurgerOrderCount(burgerOrderCount);
        this.setFryOrderCount(fryOrderCount);
        this.setDrinkOrderCount(drinkOrderCount);
        this.setIndex(index);
        this.setHasFood(false);
    }

    /**
     * Initializes a diner instance given a list of parameters.
     *
     * @param diner a list of parameters
     */
    public Diner(int[] diner, int index) {
        this.setArrivalTime(diner[0]);
        this.setBurgerOrderCount(diner[1]);
        this.setFryOrderCount(diner[2]);
        this.setDrinkOrderCount(diner[3]);
        this.setIndex(index);
    }

    /**
     * @return the arrivalTime
     */
    public int getArrivalTime() {
        return arrivalTime;
    }

    /**
     * @param arrivalTime the arrivalTime to set
     */
    private void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * @return the burgerOrderCount
     */
    public int getBurgerOrderCount() {
        return burgerOrderCount;
    }

    /**
     * @param burgerOrderCount the burgerOrderCount to set
     */
    private void setBurgerOrderCount(int burgerOrderCount) {
        this.burgerOrderCount = burgerOrderCount;
    }

    /**
     * @return the fryOrderCount
     */
    public int getFryOrderCount() {
        return fryOrderCount;
    }

    /**
     * @param fryOrderCount the fryOrderCount to set
     */
    private void setFryOrderCount(int fryOrderCount) {
        this.fryOrderCount = fryOrderCount;
    }

    /**
     * @return the drinkOrderCount
     */
    public int getDrinkOrderCount() {
        return drinkOrderCount;
    }

    /**
     * @param drinkOrderCount the drinkOrderCount to set
     */
    private void setDrinkOrderCount(int drinkOrderCount) {
        this.drinkOrderCount = drinkOrderCount;
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
     * @return the hasFood
     */
    public boolean hasFood() {
        return hasFood;
    }

    /**
     * @param hasFood the hasFood to set
     */
    public void setHasFood(boolean hasFood) {
        this.hasFood = hasFood;
    }

    /**
     * @return the resources
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public Diner with(Resources resources) {
        this.resources = resources;
        return this;
    }

    /**
     * Converts the diner to a string.
     * 
     * @return the diner as a string
     */
    @Override
    public String toString() {
        String output = "Arrival Time: %d\nBurger Order Count: %d\nFry Order Count: %d\nSoda Order Count: %d";
        return String.format(output, this.getArrivalTime(), this.getBurgerOrderCount(), this.getFryOrderCount(),
                this.getDrinkOrderCount());
    }

    /**
     * Seats the diner when a table becomes available.
     */
    public void sit() {
        this.getResources().machineLoop(this.getArrivalTime(), 0);
        this.getResources().takeTable(this);
        String seated = String.format("Diner %d seated @ table %d", this.getIndex(),
                this.getResources().getTakenTableCount());
        this.getResources().log(seated);
    }

    /**
     * Waits until the food arrives.
     */
    public void waitForFood() {
        while (!this.hasFood()) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Begins eating and only ends after EAT_TIME minutes.
     */
    public void eat() {
        int startTime = this.getResources().getGlobalClock();
        String output = String.format("Diner %d begins eating", this.getIndex());
        this.getResources().log(output);
        this.getResources().machineLoop(startTime, Restaurant.EAT_TIME);
        this.getResources().freeTable();
        String msg = String.format("Diner %d leaves diner", this.getIndex());
        this.getResources().log(msg);
    }

    /**
     * Makes a call to the sit code.
     */
    @Override
    public void run() {
        sit();
        waitForFood();
        eat();
    }

}
