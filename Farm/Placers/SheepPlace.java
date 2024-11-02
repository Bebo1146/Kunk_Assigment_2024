package Farm.Placers;

import Area.Area;
import Area.FieldType;

public class SheepPlace {
    public static Area PlaceSheeps(Area area){
        int lengthByThree = area.GetLength() / 3;
        int widthByThree = area.GetWidth() / 3;

        Tuple<Integer, Integer> sheepsStart = GetStartFrom(lengthByThree, widthByThree);

        Tuple<Integer, Integer> sheepsFinish = GetFinishFrom(area, lengthByThree, widthByThree);

        AddSheepsTo(area, sheepsStart, sheepsFinish);

        return area;
    }

    private static Tuple<Integer, Integer> GetStartFrom(int lengthByThree, int widthByThree){
        return new Tuple<Integer, Integer>(lengthByThree + 1, widthByThree + 1);
    }

    private static Tuple<Integer, Integer> GetFinishFrom(Area area, int lengthByThree, int widthByThree){
        int finishX = area.GetLength() - lengthByThree - 1;
        int finishY = area.GetWidth() - widthByThree - 1;

        return new Tuple<Integer, Integer>(finishX, finishY);
    }

    private static void AddSheepsTo(Area area, Tuple<Integer, Integer> sheepsStart, Tuple<Integer, Integer> sheepsFinish){
        int y = sheepsStart.y;
        while(y < sheepsFinish.y){
            AddSheepsVerticalyTo(area, sheepsStart.x, sheepsFinish.x, y);
            y++;
        }
    }

    private static void AddSheepsVerticalyTo(Area area, int fromX, int toX, int y){
        int counter = fromX;
        while(counter < toX){
            area.AddTypeTo(FieldType.SHEEP, counter, y);
            counter++;
        }
    }
}
