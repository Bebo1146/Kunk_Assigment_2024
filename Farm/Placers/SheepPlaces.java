package Farm.Placers;

import Area.Area;

public class SheepPlaces {
    public static IndexPair GetStartFrom(int lengthByThree, int widthByThree){
        return new IndexPair(lengthByThree + 1, widthByThree + 1);
    }

    public static IndexPair GetFinishFrom(Area area, int lengthByThree, int widthByThree){
        int finishX = area.GetLength() - lengthByThree - 1;
        int finishY = area.GetWidth() - widthByThree - 1;

        return new IndexPair(finishX, finishY);
    }
}
