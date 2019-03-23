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
    private Resources resources;

    /**
     * Initializes a diner instance given four parameters.
     *
     * @param arrivalTime the arrival time of the diner
     * @param burgerOrderCount the number of burgers the diner orders
     * @param fryOrderCount the number of fries the diner orders
     * @param drinkOrderCount the number of drinks the diner orders
     */
    public Diner(int arrivalTime, int burgerOrderCount, int fryOrderCount, int drinkOrderCount) {
        this.setArrivalTime(arrivalTime);
        this.setBurgerOrderCount(burgerOrderCount);
        this.setFryOrderCount(fryOrderCount);
        this.setDrinkOrderCount(drinkOrderCount);
    }

    /**
     * Initializes a diner instance given a list of parameters.
     *
     * @param diner a list of parameters
     */
    public Diner(int[] diner) {
        this.setArrivalTime(diner[0]);
        this.setBurgerOrderCount(diner[1]);
        this.setFryOrderCount(diner[2]);
        this.setDrinkOrderCount(diner[3]);
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
    public synchronized void sit() {
        this.getResources().takeTable(this);
        System.out.println("Diner seated");
    }

    @Override
    public void run() {
        sit();
    }

}
