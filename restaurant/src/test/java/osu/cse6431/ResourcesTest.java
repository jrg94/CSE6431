package osu.cse6431;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ResourcesTest {

    @Test
    public void testTakeTable() {
        Resources resources = new Resources(5, 100);
        Diner diner = new Diner(0, 2, 1, 1);
        resources.takeTable(diner);
        assertEquals(4, resources.getAvailableTableCount());
    }

}
