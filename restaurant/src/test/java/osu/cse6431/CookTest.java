package osu.cse6431;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class CookTest {

    @Test
    public void test() {
        Diner diner = new Diner(0, 2, 1, 1, 0);
        Cook cook = new Cook(0);
        assertFalse(diner.hasFood());
        cook.serveOrder(diner);
        assertTrue(diner.hasFood());
    }

}
