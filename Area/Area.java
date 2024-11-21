package Area;

import java.util.ArrayList;

import Area.Placers.DogPlacer;
import Area.Placers.GatePlacer;
import Area.Placers.SheepPlacer;
import Area.Placers.WallPlacer;
import FieldEntities.EmptyField;
import FieldEntities.Juh;
import FieldEntities.Kutya;

public class Area {
    public int getLength() {
        return area.length;
    }

    public int getWidth() {
        return area[0].length; 
    }

    public ArrayList<Juh> getSheeps() {
        return sheepsOnArea;
    }

    public ArrayList<Kutya> getDogs() {
        return dogsOnArea;
    }

    public Area(int length, int width) {
        area = new Field[length][width];
        init();

        WallPlacer.placeTo(this);
        GatePlacer.placeTo(this);
    }

    public Area addAmountOfSheeps(int amountOfSheeps, int waitTimeForSheepsMilliseconds) {
        SheepPlacer sheepPlacer = new SheepPlacer(this, amountOfSheeps, waitTimeForSheepsMilliseconds);
        sheepPlacer.placeSheeps();

        sheepsOnArea = sheepPlacer.getPlacedSheeps();

        return this;
    }

    public Area addAmountOfDogs(int amountOfDogs, int waitTimeForDogsMilliseconds) {
        DogPlacer dogPlacer = new DogPlacer(this, amountOfDogs, waitTimeForDogsMilliseconds);
        dogPlacer.placeDogs();

        dogsOnArea = dogPlacer.getPlacedDogs();

        return this;
    }

    public void display() {
        for (Field[] row : area) {
            for (Field field : row) {
                System.out.print(field.toString());
            }

            System.out.println();
        }
    }

    public Field getField(int x, int y) {
        return area[x][y];
    }

    public void addTypeTo(IndexPair indexPair, Field field) {
        area[indexPair.getX()][indexPair.getY()] = field;
    }

    private void init() {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                area[i][j] = new Field(i, j, new EmptyField(), false);
            }
        }
    }

    private Field[][] area;
    private ArrayList<Juh> sheepsOnArea;
    private ArrayList<Kutya> dogsOnArea;
}
