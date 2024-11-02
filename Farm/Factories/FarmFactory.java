package Farm.Factories;

import Area.Adjustment;
import Area.Area;
import Area.FieldType;
import Farm.Farm;
import Farm.Defaults.FarmAnimals;
import Farm.Placers.SheepPlace;

public class FarmFactory {
    
    public static Farm Create(int length, int width) {
        //TODO
        // if ((length % 3 != 2) || (width % 3 != 2)) {
        //     throw new IllegalArgumentException("A farm hossza és szélessége legyen 3 valamely többszöröse + 2.");
        // }

        Area area = CreateAreaForFarm(length + Adjustment.Value, width + Adjustment.Value);

        SheepPlace.PlaceSheeps(area);

        return new Farm(area);
    }

    private static Area CreateAreaForFarm(int length, int width) {
        Area area = new Area(length, width);

        return area;
    }
}
