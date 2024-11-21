package Area.Placers;

import Area.Adjustment;
import Area.Area;
import Area.IndexPair;

public class SheepPlaces {
    public SheepPlaces(Area area) {
        this.lengthByThree = (area.getLength() - Adjustment.VALUE) / 3;
        this.widthByThree = (area.getWidth() - Adjustment.VALUE) / 3;
        this.area = area;
    }

    public IndexPair getStart(){
        return new IndexPair(lengthByThree + 1, widthByThree + 1);
    }

    public IndexPair getFinish(){
        int finishX = area.getLength() - lengthByThree - 1;
        int finishY = area.getWidth() - widthByThree - 1;

        return new IndexPair(finishX, finishY);
    }

    private final Area area;
    private final int lengthByThree;
    private final int widthByThree;
}
