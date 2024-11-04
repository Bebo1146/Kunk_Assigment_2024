package Area;

import java.util.ArrayList;

import Area.Placers.GatePlacer;
import Area.Placers.SheepPlacer;
import Area.Placers.WallPlacer;
import FieldEntities.EmptyField;
import FieldEntities.Juh;

public class Area {
    public int GetLength() {
        return area.length;
    }

    public int GetWidth() {
        return area[0].length; 
    }

    public ArrayList<Juh> GetSheeps() {
        return sheepsOnArea;
    }

    public Area(int length, int width) {
        area = new Field[length][width];
        Init();

        WallPlacer.PlaceTo(this);
        GatePlacer.PlaceTo(this);
    }

    public Area AddAmountOfSheeps(int amountOfSheeps, int waitTimeForSheepsMilliseconds) {
        SheepPlacer sheepPlacer = new SheepPlacer(this, amountOfSheeps, waitTimeForSheepsMilliseconds);
        sheepPlacer.PlaceSheeps();

        sheepsOnArea = sheepPlacer.GetPlacedSheeps();

        return this;
    }

    public void Display() {
        for (Field[] row : area) {
            for (Field field : row) {
                System.out.print(field.toString());
            }

            System.out.println();
        }
    }

    public Field GetField(int x, int y) {
        return area[x][y];
    }

    public void AddTypeTo(IndexPair indexPair, Field field) {
        area[indexPair.GetX()][indexPair.GetY()] = field;
    }

    private void Init() {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                area[i][j] = new Field(i, j, new EmptyField());
            }
        }
    }

    private Field[][] area;
    private ArrayList<Juh> sheepsOnArea;
}
