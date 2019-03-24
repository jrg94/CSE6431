package osu.cse6431;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class RestaurantTest {

    private File getFile(String fileName) {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        return file;
    }

    @Test
    public void testLoad() throws IOException {
        Scanner input = new Scanner(getFile("3_diners_5_tables_1_cook.txt"));
        Restaurant test = Restaurant.load(input);
        assertEquals("Failed to load number of diners", 3, test.getDiners().size());
        assertEquals("Failed to load number of tables", 5, test.getNumberOfTables());
        assertEquals("Failed to load number of cooks", 1, test.getNumberOfCooks());
        assertEquals("Failed to load first diner's arrival time", 0, test.getDiners().get(0).getArrivalTime());
        assertEquals("Failed to load first diner's burger order", 2, test.getDiners().get(0).getBurgerOrderCount());
        assertEquals("Failed to load first diner's fry order", 1, test.getDiners().get(0).getFryOrderCount());
        assertEquals("Failed to load first diner's drink order", 0, test.getDiners().get(0).getDrinkOrderCount());
    }

}
