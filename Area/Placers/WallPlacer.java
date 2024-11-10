package Area.Placers;

import Area.Area;
import Area.Field;
import Area.IndexPair;
import FieldEntities.Fal;

public class WallPlacer {
    public static void PlaceTo(Area area){
        AddWallsTopBottom(area);
        AddWallsLeftRight(area);
    }

    private static void AddWallsTopBottom(Area area) {
        for (int i = 0; i < area.GetWidth(); i++) {
            AddWallTopFrom(area, i);
            AddWallBottomFrom(area, i);
        }
    }

    private static void AddWallTopFrom(Area area, int y) {
        IndexPair indexPair = new IndexPair(0, y);
        Field field = new Field(indexPair.GetX(), indexPair.GetY(), new Fal(), false);

        area.AddTypeTo(indexPair, field);
    }

    private static void AddWallBottomFrom(Area area, int y) {
        IndexPair indexPair = new IndexPair(area.GetLength() - 1, y);
        Field field = new Field(indexPair.GetX(), indexPair.GetY(), new Fal(), false);

        area.AddTypeTo(indexPair, field);
    }

    private static void AddWallsLeftRight(Area area) {
        for (int i = 0; i < area.GetLength(); i++) {
            AddWallLeftFrom(area, i);
            AddWallRightFrom(area, i);
        }
    }

    private static void AddWallLeftFrom(Area area, int x) {
        IndexPair indexPair = new IndexPair(x, 0);
        Field field = new Field(indexPair.GetX(), indexPair.GetY(), new Fal(), false);

        area.AddTypeTo(indexPair, field);
    }

    private static void AddWallRightFrom(Area area, int x) {
        IndexPair indexPair = new IndexPair(x, area.GetWidth() - 1);
        Field field = new Field(indexPair.GetX(), indexPair.GetY(), new Fal(), false);

        area.AddTypeTo(indexPair, field);
    }
}
