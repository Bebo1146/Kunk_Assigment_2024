import Farm.Factories.FarmFactory;
import Farm.Farm;

class Program {
    public static void main(String[] args) {
        int length = 12;
        int witdh = 12;
        int waitTimeForSheepsMilliseconds = 200;
        int waitTimeForDogsMilliseconds = 200;
        int numberOfDogs = 5;
        int numberOfSheeps = 10;

        Farm farm = FarmFactory.Create(length, witdh, numberOfDogs, numberOfSheeps);
        farm.DisplayArea();
    }
}