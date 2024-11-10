package Farm;

import java.util.ArrayList;

import Area.Area;
import Area.Observing.IGateReachedListener;
import FieldEntities.Juh;
import FieldEntities.Kutya;

public class Farm {
    public Farm(Area area) {
        this.area = area;
        this.sheepsOnArea = area.GetSheeps();
        this.dogsOnArea = area.GetDogs();
    }

    public void DisplayArea() {
        area.Display();
    }

    public void StartMovingSheeps(IGateReachedListener gateReachedListener) {
        sheepsOnArea.forEach(sheep -> 
        {
            sheep.AddGateReachedListener(gateReachedListener);
            sheep.start();
        });
    }

    public void StartMovingDogs() {
        dogsOnArea.forEach(dog -> dog.start());
    }

    private final Area area;
    private final ArrayList<Juh> sheepsOnArea;
    private final ArrayList<Kutya> dogsOnArea;
}