package osu.cse6431;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ResourcesTest {

    @Test
    public void testTakeTable() throws InterruptedException {
        Resources resources = new Resources(5, 100);
        Diner diner = new Diner(0, 2, 1, 1, 0);
        resources.takeTable(diner);
        assertEquals("Failed to take available table", 1, resources.getTakenTableCount());
        assertEquals("Failed to add diner to list of active diners", diner, resources.getActiveDiners().take());
    }

    @Test
    public void testFreeTable() {
        Resources resources = new Resources(5, 100);
        Diner diner = new Diner(0, 2, 1, 1, 0);
        resources.takeTable(diner);
        resources.freeTable();
        assertEquals("Failed to free table for use", 0, resources.getTakenTableCount());
        assertEquals("Failed to increment total served count", 1, resources.getServedDinerCount());
    }

    @Test
    public void testTakeOrder() {
        Resources resources = new Resources(5, 100);
        Diner diner = new Diner(0, 2, 1, 1, 0);
        resources.takeTable(diner);
        resources.takeOrder();
        assertTrue("Failed to remove diner from list of active diners", resources.getActiveDiners().isEmpty());
    }

    @Test
    public void testIncrementClock() {
        Resources resources = new Resources(5, 100);
        assertEquals("Failed to initialize global clock", 0, resources.getGlobalClock());
        resources.incrementClock();
        assertEquals("Failed to increment global clock", 1, resources.getGlobalClock());
    }

    @Test
    public void testMachineLoop() {
        Resources resources = new Resources(5, 100);
        resources.machineLoop(0, 0);
    }

    @Test
    public void testHasMoreDiners() {
        Resources resources = new Resources(5, 1);
        assertTrue("Failed to detect more diners", resources.hasMoreDiners());
        resources.freeTable();
        assertFalse("Failed to detect no more diners", resources.hasMoreDiners());
    }
}
