/**
 * 
 */
package osu.cse6431;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jeremy Grifski
 *
 */
public class Restaurant {

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int numberOfDiners = Integer.parseInt(input.nextLine());
        int numberOfTables = Integer.parseInt(input.nextLine());
        int numberOfCooks = Integer.parseInt(input.nextLine());
        for (int i = 0; i < numberOfDiners; i++) {
            String diner = input.nextLine();
            int[] values = Arrays.stream(diner.split(" ")).mapToInt(Integer::parseInt).toArray();
        }
        input.close();
    }

}
