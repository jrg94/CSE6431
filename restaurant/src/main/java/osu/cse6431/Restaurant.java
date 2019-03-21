package osu.cse6431;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * A restaurant simulator.
 * 
 * @author Jeremy Grifski
 */
public class Restaurant {

    private ArrayList<Diner> diners;
    private int numberOfTables;
    private int numberOfCooks;

    /**
     * Initializes a restaurant given three parameters.
     * 
     * @param numberOfTables the number of tables in the restaurant
     * @param numberOfCooks the number of cooks in the restaurant
     * @param diners a collection of diners
     */
    public Restaurant(int numberOfTables, int numberOfCooks, ArrayList<Diner> diners) {
        this.numberOfCooks = numberOfCooks;
        this.numberOfTables = numberOfTables;
        this.diners = diners;
    }

    /**
     * Generates a restaurant from data given on standard input.
     * 
     * @return a new restaurant as specified by standard input
     */
    public static Restaurant load() {
        Scanner input = new Scanner(System.in);
        int numberOfDiners = Integer.parseInt(input.nextLine());
        int numberOfTables = Integer.parseInt(input.nextLine());
        int numberOfCooks = Integer.parseInt(input.nextLine());
        ArrayList<Diner> diners = new ArrayList<Diner>();
        for (int i = 0; i < numberOfDiners; i++) {
            String diner = input.nextLine();
            int[] values = Arrays.stream(diner.split(" ")).mapToInt(Integer::parseInt).toArray();
            diners.add(new Diner(values));
        }
        input.close();
        Restaurant ret = new Restaurant(numberOfTables, numberOfCooks, diners);
        return ret;
    }

    /**
     * Launches a restaurant instance.
     * 
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Restaurant toRun = Restaurant.load();
    }

}
