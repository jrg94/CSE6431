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

    public static final int BURGER_COOK_TIME = 5; // minutes
    public static final int FRY_COOK_TIME = 3; // minutes
    public static final int SODA_FILL_TIME = 1; // minutes

    private ArrayList<Diner> diners;
    private ArrayList<Cook> cooks;
    private int numberOfTables;
    private int numberOfCooks;

    /**
     * Initializes a restaurant given three parameters.
     * 
     * @param numberOfTables the number of tables in the restaurant
     * @param numberOfCooks the number of cooks in the restaurant
     * @param diners a collection of diners
     */
    public Restaurant(int numberOfTables, int numberOfCooks, ArrayList<Diner> diners, ArrayList<Cook> cooks) {
        this.setNumberOfCooks(numberOfCooks);
        this.setNumberOfTables(numberOfTables);
        this.setDiners(diners);
        this.setCooks(cooks);
    }

    /**
     * @return the diners
     */
    public ArrayList<Diner> getDiners() {
        return diners;
    }

    /**
     * @param diners the diners to set
     */
    private void setDiners(ArrayList<Diner> diners) {
        this.diners = diners;
    }

    /**
     * @return the numberOfTables
     */
    public int getNumberOfTables() {
        return numberOfTables;
    }

    /**
     * @param numberOfTables the numberOfTables to set
     */
    private void setNumberOfTables(int numberOfTables) {
        this.numberOfTables = numberOfTables;
    }

    /**
     * @return the numberOfCooks
     */
    public int getNumberOfCooks() {
        return numberOfCooks;
    }

    /**
     * @param numberOfCooks the numberOfCooks to set
     */
    private void setNumberOfCooks(int numberOfCooks) {
        this.numberOfCooks = numberOfCooks;
    }

    /**
     * @return the cooks
     */
    public ArrayList<Cook> getCooks() {
        return cooks;
    }

    /**
     * @param cooks the cooks to set
     */
    private void setCooks(ArrayList<Cook> cooks) {
        this.cooks = cooks;
    }

    private static ArrayList<Diner> loadDiners(Scanner input, int numberOfDiners) {
        ArrayList<Diner> diners = new ArrayList<Diner>();
        for (int i = 0; i < numberOfDiners; i++) {
            String diner = input.nextLine();
            int[] values = Arrays.stream(diner.split(" ")).mapToInt(Integer::parseInt).toArray();
            diners.add(new Diner(values));
        }
        return diners;
    }

    private static ArrayList<Cook> loadCooks(int numberOfCooks) {
        ArrayList<Cook> cooks = new ArrayList<Cook>();
        for (int i = 0; i < numberOfCooks; i++) {
            cooks.add(new Cook());
        }
        return cooks;
    }

    /**
     * Generates a restaurant from data given on standard input.
     * 
     * @return a new restaurant as specified by standard input
     */
    public static Restaurant load(Scanner input) {
        int numberOfDiners = Integer.parseInt(input.nextLine());
        int numberOfTables = Integer.parseInt(input.nextLine());
        int numberOfCooks = Integer.parseInt(input.nextLine());
        ArrayList<Diner> diners = loadDiners(input, numberOfDiners);
        ArrayList<Cook> cooks = loadCooks(numberOfCooks);
        Restaurant ret = new Restaurant(numberOfTables, numberOfCooks, diners, cooks);
        return ret;
    }

    /**
     * Launches a restaurant instance.
     * 
     * @param args the input arguments
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Scanner input = new Scanner(System.in);
        Restaurant toRun = Restaurant.load(input);
        input.close();

        Resources resources = new Resources(toRun.getNumberOfTables());
        for (Diner diner : toRun.getDiners()) {
            diner.with(resources).start();
        }

        for (Cook cook : toRun.getCooks()) {
            cook.with(resources).start();
        }
    }

}
