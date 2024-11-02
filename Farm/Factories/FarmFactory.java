package Farm.Factories;

import Area.Adjustment;
import Area.Area;
import Farm.Farm;
import Farm.Placers.DogPlacer;
import Farm.Placers.SheepPlacer;

public class FarmFactory {
    
    public static Farm Create(int length, int width, int numberOfDogs, int numberOfSheeps) {
        //TODO
        // if ((length % 3 != 2) || (width % 3 != 2)) {
        //     throw new IllegalArgumentException("A farm hossza és szélessége legyen 3 valamely többszöröse + 2.");
        // }

        Area area = CreateAreaForFarm(length + Adjustment.Value, width + Adjustment.Value);

        SheepPlacer.PlaceSheeps(area, numberOfSheeps);
        DogPlacer.PlaceTo(area, numberOfDogs);

        return new Farm(area);
    }

    private static Area CreateAreaForFarm(int length, int width) {
        Area area = new Area(length, width);

        return area;
    }
}
