package osu.cse6431;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DinerTest {

    @Test
    public void testSit() {
        Resources resources = new Resources(5, 100);
        Diner diner = new Diner(0, 2, 1, 0, 0);
        diner.with(resources).sit();
        assertEquals(0, resources.getGlobalClock());
        assertTrue(resources.getActiveDiners().contains(diner));
    }

}
