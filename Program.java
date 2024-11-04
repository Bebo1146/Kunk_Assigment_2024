import Farm.Factories.FarmFactory;

import java.util.Random;

import Area.GateReachedEvent;
import Area.GateReachedListener;
import Farm.Farm;

class Program implements GateReachedListener {
    public static void main(String[] args) {
        int length = 6;
        int witdh = 6;
        int waitTimeForSheepsMilliseconds = 200;
        int waitTimeForDogsMilliseconds = 200;
        int numberOfDogs = 5;
        int numberOfSheeps = 10;

        Farm farm = FarmFactory.Create(length, witdh, 2, 3,
            waitTimeForSheepsMilliseconds, waitTimeForDogsMilliseconds);

        farm.StartMovingSheeps();
        farm.DisplayArea();
        while (true) {

            System.out.println();
            try {
                Thread.sleep(waitTimeForSheepsMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println();

            farm.DisplayArea();
        }
    }

    @Override
    public void OnGateReached(GateReachedEvent event) {
        System.out.println("Juh " + event.getJuh().getName() + " has reached the gate!");
    }
}