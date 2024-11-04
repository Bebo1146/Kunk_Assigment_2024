package Area.Placers;

import java.util.Random;

import Area.Area;
import Area.Field;
import Area.IndexPair;
import FieldEntities.Kapu;
import FieldEntities.Kutya;

public class GatePlacer {
    public static void PlaceTo(Area area){
        AddGatesTopBottom(area);
        AddGatesLeftRight(area);
    }

    private static void AddGatesTopBottom(Area area){
        AddGateTop(area);
        AddGateBottom(area);
    }

    private static void AddGateTop(Area area){
        int y = random.nextInt(area.GetWidth()- 2) + 1;
        IndexPair indexPair = new IndexPair(0, y);
        Field field = new Field(indexPair.GetX(), indexPair.GetY(), new Kapu());

        area.AddTypeTo(indexPair, field);
    }

    private static void AddGateBottom(Area area){
        int y = random.nextInt(area.GetWidth()- 2) + 1;
        IndexPair indexPair = new IndexPair(area.GetLength() - 1, y);
        Field field = new Field(indexPair.GetX(), indexPair.GetY(), new Kapu());

        area.AddTypeTo(indexPair, field);
    }

    private static void AddGatesLeftRight(Area area){
        AddGateLeft(area);
        AddGateRight(area);
    }
    
    private static void AddGateLeft(Area area){
        int x = random.nextInt(area.GetLength()- 2) + 1;
        IndexPair indexPair = new IndexPair(x, 0);
        Field field = new Field(indexPair.GetX(), indexPair.GetY(), new Kapu());

        area.AddTypeTo(indexPair, field);
    }

    private static void AddGateRight(Area area){
        int x = random.nextInt(area.GetLength()- 2) + 1;
        IndexPair indexPair = new IndexPair(x, area.GetWidth() - 1);
        Field field = new Field(indexPair.GetX(), indexPair.GetY(), new Kapu());

        area.AddTypeTo(indexPair, field);
    }

    private static Random random = new Random();
}
