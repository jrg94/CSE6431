package osu.cse6431;

import java.util.ArrayList;

public class Resources {

    private int availableTableCount;
    private boolean isGrillAvailable;
    private boolean isFryerAvailable;
    private boolean isSodaMachineAvailable;
    private ArrayList<Diner> activeDiners;

    public Resources(int availableTableCount) {
        this.setAvailableTableCount(availableTableCount);
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

    public void takeTable(Diner activeDiner) {
        this.setAvailableTableCount(this.getAvailableTableCount() - 1);
        this.getActiveDiners().add(activeDiner);
    }

    public Diner takeOrder() {
        return this.getActiveDiners().remove(0);
    }

}
