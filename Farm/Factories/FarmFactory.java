package Farm.Factories;

import Area.Adjustment;
import Area.Area;
import Farm.Farm;

public class FarmFactory {
    public static Farm create(int length, int width, int numberOfDogs,
        int numberOfSheeps, int waitTimeForSheepsMilliseconds ,int waitTimeForDogsMilliseconds) 
    {
        if ((length % 3 != 0) || (width % 3 != 0)) {
            throw new IllegalArgumentException("A farm hossza és szélessége legyen 3 valamely többszöröse.");
        }

        Area area = 
            createAreaForFarm(length + Adjustment.VALUE, width + Adjustment.VALUE)
            .addAmountOfSheeps(numberOfSheeps, waitTimeForSheepsMilliseconds)
            .addAmountOfDogs(numberOfDogs, waitTimeForDogsMilliseconds);

        return new Farm(area);
    }

    private static Area createAreaForFarm(int length, int width) {
        Area area = new Area(length, width);

        return area;
    }
}
