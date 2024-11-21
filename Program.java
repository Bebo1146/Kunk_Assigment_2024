import Farm.Factories.FarmFactory;

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

        Farm farm = FarmFactory.create(length, width, numberOfDogs, numberOfSheeps,
            waitTimeForSheepsMilliseconds, waitTimeForDogsMilliseconds);

        GateReachedListener gateReachedListener = new GateReachedListener();

        farm.startMovingSheeps(gateReachedListener);
        farm.startMovingDogs();

        while (!gateReachedListener.getIsGateReached()) {
            System.out.print("\u001B[0;0H");
            System.out.flush();

            farm.displayArea();

            try {
                Thread.sleep(waitTimeForSheepsMilliseconds);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        farm.stopMovingSheeps();
        farm.stopMovingDogs();
    }
}