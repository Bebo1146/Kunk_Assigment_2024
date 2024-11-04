package Farm.Factories;

import Area.Adjustment;
import Area.Area;
import Area.Placers.DogPlacer;
import Area.Placers.SheepPlacer;
import Farm.Farm;

public class FarmFactory {
    
    public static Farm Create(int length, int width, int numberOfDogs,
        int numberOfSheeps, int waitTimeForSheepsMilliseconds ,int waitTimeForDogsMilliseconds) 
    {
        if ((length % 3 != 0) || (width % 3 != 0)) {
            throw new IllegalArgumentException("A farm hossza és szélessége legyen 3 valamely többszöröse.");
        }

        Area area = 
            CreateAreaForFarm(length + Adjustment.Value, width + Adjustment.Value)
            .AddAmountOfSheeps(numberOfSheeps, waitTimeForSheepsMilliseconds);

        DogPlacer.PlaceTo(area, numberOfDogs);

        return new Farm(area, area.GetSheeps());
    }

    private static Area CreateAreaForFarm(int length, int width) {
        Area area = new Area(length, width);

        return area;
    }
}
