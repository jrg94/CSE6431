package osu.cse6431;

public class Diner {

    private int arrivalTime; // 0 to 120
    private int burgerOrderCount; // > 1
    private int fryOrderCount; // > 0
    private int drinkOrderCount; // 0 or 1

    public Diner(int arrivalTime, int burgerOrderCount, int fryOrderCount, int drinkOrderCount) {
        this.arrivalTime = arrivalTime;
        this.burgerOrderCount = burgerOrderCount;
        this.fryOrderCount = fryOrderCount;
        this.drinkOrderCount = drinkOrderCount;
    }

    public Diner(int[] diner) {
        this.arrivalTime = diner[0];
        this.burgerOrderCount = diner[1];
        this.burgerOrderCount = diner[2];
        this.burgerOrderCount = diner[3];
    }

}
