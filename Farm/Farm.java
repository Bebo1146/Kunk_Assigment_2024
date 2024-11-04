package Farm;

import java.util.ArrayList;

import Area.Area;
import FieldEntities.Juh;

public class Farm {
    public Farm(Area area, ArrayList<Juh> sheepsOnArea) {
        myArea = area;
        this.sheepsOnArea = sheepsOnArea;
    }

    public void DisplayArea() {
        myArea.Display();
    }

    public void StartMovingSheeps() {
        sheepsOnArea.forEach(sheep -> sheep.start());
    }

    private Area myArea;
    private ArrayList<Juh> sheepsOnArea;
}