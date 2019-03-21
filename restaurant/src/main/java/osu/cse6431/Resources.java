package osu.cse6431;

import java.util.ArrayList;

public class Resources {

    private int availableTableCount;
    private boolean isGrillAvailable;
    private boolean isFryerAvailable;
    private boolean isSodaMachineAvailable;
    private ArrayList<Diner> activeDiners;
    private int totalDinerCount;
    private int servedDinerCount;

    public Resources(int availableTableCount, int totalDinerCount) {
        this.setAvailableTableCount(availableTableCount);
        this.setTotalDinerCount(totalDinerCount);
        this.setServedDinerCount(0);
        this.setGrillAvailable(true);
        this.setFryerAvailable(true);
        this.setSodaMachineAvailable(true);
        this.setActiveDiners(new ArrayList<Diner>());
    }

    /**
     * @return the availableTableCount
     */
    public int getAvailableTableCount() {
        return availableTableCount;
    }

    /**
     * @param availableTableCount the availableTableCount to set
     */
    private void setAvailableTableCount(int availableTableCount) {
        this.availableTableCount = availableTableCount;
    }

    /**
     * @return the isGrillAvailable
     */
    public boolean isGrillAvailable() {
        return isGrillAvailable;
    }

    /**
     * @param isGrillAvailable the isGrillAvailable to set
     */
    private void setGrillAvailable(boolean isGrillAvailable) {
        this.isGrillAvailable = isGrillAvailable;
    }

    /**
     * @return the isFryerAvailable
     */
    public boolean isFryerAvailable() {
        return isFryerAvailable;
    }

    /**
     * @param isFryerAvailable the isFryerAvailable to set
     */
    private void setFryerAvailable(boolean isFryerAvailable) {
        this.isFryerAvailable = isFryerAvailable;
    }

    /**
     * @return the isSodaMachineAvailable
     */
    public boolean isSodaMachineAvailable() {
        return isSodaMachineAvailable;
    }

    /**
     * @param isSodaMachineAvailable the isSodaMachineAvailable to set
     */
    private void setSodaMachineAvailable(boolean isSodaMachineAvailable) {
        this.isSodaMachineAvailable = isSodaMachineAvailable;
    }

    public ArrayList<Diner> getActiveDiners() {
        return this.activeDiners;
    }

    private void setActiveDiners(ArrayList<Diner> activeDiners) {
        this.activeDiners = activeDiners;
    }

    /**
     * @return the totalDinerCount
     */
    public int getTotalDinerCount() {
        return totalDinerCount;
    }

    /**
     * @param totalDinerCount the totalDinerCount to set
     */
    private void setTotalDinerCount(int totalDinerCount) {
        this.totalDinerCount = totalDinerCount;
    }

    /**
     * @return the servedDinerCount
     */
    public int getServedDinerCount() {
        return servedDinerCount;
    }

    /**
     * @param servedDinerCount the servedDinerCount to set
     */
    private void setServedDinerCount(int servedDinerCount) {
        this.servedDinerCount = servedDinerCount;
    }

    /**
     * Takes the latest diner off the active diner list.
     * 
     * @return the latest diner
     */
    public Diner takeOrder() {
        return this.getActiveDiners().remove(0);
    }

    /**
     * Frees up a table for new diners and increases the served diner count.
     */
    public void freeTable() {
        this.setAvailableTableCount(this.getAvailableTableCount() + 1);
        this.setServedDinerCount(this.getServedDinerCount() + 1);
    }

    /**
     * Takes a table and adds the diner that took it to the active diners lists.
     * 
     * @param activeDiner the diner taking the table
     */
    public void takeTable(Diner activeDiner) {
        this.setAvailableTableCount(this.getAvailableTableCount() - 1);
        this.getActiveDiners().add(activeDiner);
    }
}
