package Area.Placers;

import Area.Area;
import Area.Field;
import Area.IndexPair;
import FieldEntities.Fal;

public class WallPlacer {
    public static void placeTo(Area area){
        addWallsTopBottom(area);
        addWallsLeftRight(area);
    }

    private static void addWallsTopBottom(Area area) {
        for (int i = 0; i < area.getWidth(); i++) {
            addWallTopFrom(area, i);
            addWallBottomFrom(area, i);
        }
    }

    private static void addWallTopFrom(Area area, int y) {
        IndexPair indexPair = new IndexPair(0, y);
        Field field = new Field(indexPair.getX(), indexPair.getY(), new Fal(), false);

        area.addTypeTo(indexPair, field);
    }

    private static void addWallBottomFrom(Area area, int y) {
        IndexPair indexPair = new IndexPair(area.getLength() - 1, y);
        Field field = new Field(indexPair.getX(), indexPair.getY(), new Fal(), false);

        area.addTypeTo(indexPair, field);
    }

    private static void addWallsLeftRight(Area area) {
        for (int i = 0; i < area.getLength(); i++) {
            addWallLeftFrom(area, i);
            addWallRightFrom(area, i);
        }
    }

    private static void addWallLeftFrom(Area area, int x) {
        IndexPair indexPair = new IndexPair(x, 0);
        Field field = new Field(indexPair.getX(), indexPair.getY(), new Fal(), false);

        area.addTypeTo(indexPair, field);
    }

    private static void addWallRightFrom(Area area, int x) {
        IndexPair indexPair = new IndexPair(x, area.getWidth() - 1);
        Field field = new Field(indexPair.getX(), indexPair.getY(), new Fal(), false);

        area.addTypeTo(indexPair, field);
    }
}
