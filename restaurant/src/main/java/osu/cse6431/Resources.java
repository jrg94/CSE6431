package osu.cse6431;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class Resources {

    private BlockingQueue<Diner> activeDiners;
    private int totalDinerCount;
    private int servedDinerCount;
    private int totalTableCount;
    private int takenTableCount;
    private int globalClock;

    /**
     * The resources constructor.
     * 
     * @param totalTableCount the number of available tables
     * @param totalDinerCount
     */
    public Resources(int totalTableCount, int totalDinerCount) {
        this.setTotalTableCount(totalTableCount);
        this.setTakenTableCount(0);
        this.setTotalDinerCount(totalDinerCount);
        this.setServedDinerCount(0);
        this.setActiveDiners(new LinkedBlockingDeque<Diner>(this.getTotalTableCount()));
        this.setGlobalClock(0);
    }

    /**
     * @return the availableTableCount
     */
    public int getTotalTableCount() {
        return totalTableCount;
    }

    /**
     * @param availableTableCount the availableTableCount to set
     */
    private void setTotalTableCount(int availableTableCount) {
        this.totalTableCount = availableTableCount;
    }

    /**
     * @return the takenTableCount
     */
    public int getTakenTableCount() {
        return takenTableCount;
    }

    /**
     * @param takenTableCount the takenTableCount to set
     */
    public void setTakenTableCount(int takenTableCount) {
        this.takenTableCount = takenTableCount;
    }

    /**
     * @return the list of active diners
     */
    public BlockingQueue<Diner> getActiveDiners() {
        return this.activeDiners;
    }

    /**
     * @param activeDiners the activeDiners to set
     */
    private void setActiveDiners(LinkedBlockingDeque<Diner> activeDiners) {
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
     * @return the globalClock
     */
    public int getGlobalClock() {
        return globalClock;
    }

    /**
     * @param globalClock the globalClock to set
     */
    private void setGlobalClock(int globalClock) {
        this.globalClock = globalClock;
    }

    /**
     * Takes the latest diner off the active diner list.
     * 
     * @return the latest diner
     */
    public Diner takeOrder() {
        try {
            return this.getActiveDiners().poll(10, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Frees up a table for new diners and increases the served diner count.
     */
    public void freeTable() {
        this.setTakenTableCount(this.getTakenTableCount() - 1);
        this.setServedDinerCount(this.getServedDinerCount() + 1);
    }

    /**
     * Takes a table and adds the diner that took it to the active diners lists.
     * 
     * @param activeDiner the diner taking the table
     */
    public void takeTable(Diner activeDiner) {
        this.setTakenTableCount(this.getTakenTableCount() + 1);
        try {
            this.getActiveDiners().put(activeDiner);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void incrementClock() {
        this.setGlobalClock(this.getGlobalClock() + 1);
    }

    public void log(String msg) {
        String time = String.format("T-%d: %s", this.getGlobalClock(), msg);
        System.out.println(time);
    }

    /**
     * A helper method which runs a busy loop for checking the global clock to
     * see if the machine has finished its job based on some delta.
     * 
     * @param startTime the time when the machine was first in use
     * @param delta the duration of use for that machine
     */
    public void machineLoop(int startTime, int delta) {
        while (this.getGlobalClock() < startTime + delta) {
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
