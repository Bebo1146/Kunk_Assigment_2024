package Area.Placers;

import java.util.Random;

import Area.Area;
import Area.Field;
import Area.IndexPair;
import FieldEntities.Kapu;

public class GatePlacer {
    public static void placeTo(Area area){
        addGatesTopBottom(area);
        addGatesLeftRight(area);
    }

    private static void addGatesTopBottom(Area area){
        addGateTop(area);
        addGateBottom(area);
    }

    private static void addGateTop(Area area){
        int y = random.nextInt(area.getWidth()- 2) + 1;
        IndexPair indexPair = new IndexPair(0, y);
        Field field = new Field(indexPair.getX(), indexPair.getY(), new Kapu(), false);

        area.addTypeTo(indexPair, field);
    }

    private static void addGateBottom(Area area){
        int y = random.nextInt(area.getWidth()- 2) + 1;
        IndexPair indexPair = new IndexPair(area.getLength() - 1, y);
        Field field = new Field(indexPair.getX(), indexPair.getY(), new Kapu(),  false);

        area.addTypeTo(indexPair, field);
    }

    private static void addGatesLeftRight(Area area){
        addGateLeft(area);
        addGateRight(area);
    }
    
    private static void addGateLeft(Area area){
        int x = random.nextInt(area.getLength()- 2) + 1;
        IndexPair indexPair = new IndexPair(x, 0);
        Field field = new Field(indexPair.getX(), indexPair.getY(), new Kapu(),  false);

        area.addTypeTo(indexPair, field);
    }

    private static void addGateRight(Area area){
        int x = random.nextInt(area.getLength()- 2) + 1;
        IndexPair indexPair = new IndexPair(x, area.getWidth() - 1);
        Field field = new Field(indexPair.getX(), indexPair.getY(), new Kapu(),  false);

        area.addTypeTo(indexPair, field);
    }

    private static Random random = new Random();
}
