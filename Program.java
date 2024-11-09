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

        Farm farm = FarmFactory.Create(length, width, numberOfDogs, numberOfSheeps,
            waitTimeForSheepsMilliseconds, waitTimeForDogsMilliseconds);

        GateReachedListener gateReachedListener = new GateReachedListener();

        farm.StartMovingSheeps(gateReachedListener);
        farm.StartMovingDogs();

        farm.DisplayArea();
        while (!gateReachedListener.IsGateReached) {

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
}