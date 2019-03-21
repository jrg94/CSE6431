/**
 * 
 */
package osu.cse6431;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * @author Jeremy Grifski
 *
 */
public class Restaurant {

    private ArrayList<Diner> diners;
    private int numberOfTables;
    private int numberOfCooks;

    public Restaurant(int numberOfTables, int numberOfCooks, ArrayList<Diner> diners) {
        this.numberOfCooks = numberOfCooks;
        this.numberOfTables = numberOfTables;
        this.diners = diners;
    }

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
