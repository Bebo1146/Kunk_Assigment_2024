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
            System.out.print("\u001B[0;0H");
            System.out.flush();

            farm.DisplayArea();

            try {
                Thread.sleep(waitTimeForSheepsMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.exit(0);
    }
}