package Area.Placers;

import Area.Adjustment;
import Area.Area;
import Area.IndexPair;

public class SheepPlaces {
    public SheepPlaces(Area area) {
        this.lengthByThree = (area.GetLength() - Adjustment.Value) / 3;
        this.widthByThree = (area.GetWidth() - Adjustment.Value) / 3;
        this.area = area;
    }

    public IndexPair GetStart(){
        return new IndexPair(lengthByThree + 1, widthByThree + 1);
    }

    public IndexPair GetFinish(){
        int finishX = area.GetLength() - lengthByThree - 1;
        int finishY = area.GetWidth() - widthByThree - 1;

        return new IndexPair(finishX, finishY);
    }

    private final Area area;
    private final int lengthByThree;
    private final int widthByThree;
}
