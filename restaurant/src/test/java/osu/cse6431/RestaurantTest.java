package osu.cse6431;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import org.junit.Test;

public class RestaurantTest {

    private void setInputStream(String fileName) {
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            InputStream inContent = new ByteArrayInputStream(Files.readAllBytes(file.toPath()));
            System.setIn(inContent);
        } catch (IOException e) {
            System.err.println("Failed to load resource: " + fileName);
        }
    }

    @Test
    public void testLoad() throws IOException {
        setInputStream("test_001.txt");
        Restaurant test = Restaurant.load();
        assertEquals(3, test.getDiners().size());
        assertEquals(5, test.getNumberOfTables());
        assertEquals(1, test.getNumberOfCooks());
        assertEquals(0, test.getDiners().get(0).getArrivalTime());
        assertEquals(2, test.getDiners().get(0).getBurgerOrderCount());
        assertEquals(1, test.getDiners().get(0).getFryOrderCount());
        assertEquals(0, test.getDiners().get(0).getDrinkOrderCount());
    }

}
