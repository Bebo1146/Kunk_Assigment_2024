import Farm.Factories.FarmFactory;

import java.io.IOException;

import Area.Observing.GateReachedListener;
import Farm.Farm;

class Program {
    public static void main(String[] args) {
        int length = 12;
        int width = 12;
        int waitTimeForSheepsMilliseconds = 200;
        int waitTimeForDogsMilliseconds = 200;
        int numberOfDogs = 5;
        int numberOfSheeps = 10;

        Farm farm = FarmFactory.Create(length, width, numberOfDogs, numberOfSheeps,
            waitTimeForSheepsMilliseconds, waitTimeForDogsMilliseconds);

        GateReachedListener gateReachedListener = new GateReachedListener();

        farm.StartMovingSheeps(gateReachedListener);
        farm.StartMovingDogs();

        while (!gateReachedListener.IsGateReached) {
            // Move the cursor to the top left corner
            System.out.print("\u001B[0;0H");
            System.out.flush();

            // Display the farm state
            farm.DisplayArea();

            // Wait for the specified time before updating again
            try {
                Thread.sleep(waitTimeForSheepsMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}