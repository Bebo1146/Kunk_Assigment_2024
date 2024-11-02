package Farm.Placers;

import Area.Adjustment;
import Area.Area;
import Area.FieldType;

public class SheepPlacer {
    public static Area PlaceSheeps(Area area, int amountOfSheeps){
        int lengthByThree = (area.GetLength() - Adjustment.Value) / 3;
        int widthByThree = (area.GetWidth() - Adjustment.Value) / 3;

        IndexPair sheepsStart = 
            SheepPlaces.GetStartFrom(lengthByThree, widthByThree);

        IndexPair sheepsFinish = 
            SheepPlaces.GetFinishFrom(area, lengthByThree, widthByThree);

        AddSheepsTo(area, amountOfSheeps, sheepsStart, sheepsFinish);

        return area;
    }

    private static void AddSheepsTo(Area area, int amountOfSheeps, IndexPair sheepsStart, IndexPair sheepsFinish){
        int y = sheepsStart.Y;
        while(y < sheepsFinish.Y){
            amountOfSheeps = AddSheepsVerticalyTo(area, sheepsStart.X, sheepsFinish.X, y, amountOfSheeps);
            y++;
        }
    }

    private static int AddSheepsVerticalyTo(Area area, int fromX, int toX, int y, int amountOfSheeps){
        int counter = fromX;
        while(counter < toX && amountOfSheeps > 0){
            area.AddTypeTo(FieldType.SHEEP, counter, y);
            counter++;
            amountOfSheeps--;
        }

        System.out.println();

        return amountOfSheeps;
    }
}
