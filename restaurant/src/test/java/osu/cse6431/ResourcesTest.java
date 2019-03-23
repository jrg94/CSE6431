package osu.cse6431;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class ResourcesTest {

    @Test
    public void testTakeTable() throws InterruptedException {
        Resources resources = new Resources(5, 100);
        Diner diner = new Diner(0, 2, 1, 1);
        resources.takeTable(diner);
        assertEquals("Failed to take available table", 1, resources.getTakenTableCount());
        assertEquals("Failed to add diner to list of active diners", diner, resources.getActiveDiners().take());
    }

    @Test
    public void testFreeTable() {
        Resources resources = new Resources(5, 100);
        Diner diner = new Diner(0, 2, 1, 1);
        resources.takeTable(diner);
        resources.freeTable();
        assertEquals("Failed to free table for use", 0, resources.getTakenTableCount());
        assertEquals("Failed to increment total served count", 1, resources.getServedDinerCount());
    }

    @Test
    public void testTakeOrder() {
        Resources resources = new Resources(5, 100);
        Diner diner = new Diner(0, 2, 1, 1);
        resources.takeTable(diner);
        resources.takeOrder();
        assertTrue("Failed to remove diner from list of active diners", resources.getActiveDiners().isEmpty());
    }

}