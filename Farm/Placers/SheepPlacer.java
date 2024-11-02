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
        int x = sheepsStart.X;
        while(x < sheepsFinish.X){
            amountOfSheeps = AddSheepsVerticalyTo(area, sheepsStart.Y, sheepsFinish.Y, x, amountOfSheeps);
            x++;
        }
    }

    private static int AddSheepsVerticalyTo(Area area, int fromY, int toY, int x, int amountOfSheeps){
        int counter = fromY;
        while(counter < toY && amountOfSheeps > 0){
            area.AddTypeTo(FieldType.SHEEP, x, counter);
            counter++;
            amountOfSheeps--;
        }
        
        return amountOfSheeps;
    }
}