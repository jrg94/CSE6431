package restaurant;

import org.junit.Test;

import osu.cse6431.Diner;

public class DinnerTest {

    @Test
    public void testRun() {
        Diner diner = new Diner(0, 2, 1, 0);
        diner.run();
    }

}
