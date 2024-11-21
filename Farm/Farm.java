package Farm;

import java.util.ArrayList;

import Area.Area;
import Area.Observing.IGateReachedListener;
import FieldEntities.Juh;
import FieldEntities.Kutya;

public class Farm {
    public Farm(Area area) {
        this.area = area;
        this.sheepsOnArea = area.getSheeps();
        this.dogsOnArea = area.getDogs();
    }

    public void displayArea() {
        area.display();
    }

    public void startMovingSheeps(IGateReachedListener gateReachedListener) {
        sheepsOnArea.forEach(sheep -> 
        {
            sheep.addGateReachedListener(gateReachedListener);
            sheep.start();
        });
    }

    public void startMovingDogs() {
        dogsOnArea.forEach(dog -> dog.start());
    }

    public void stopMovingSheeps() {
        sheepsOnArea.forEach(dog -> dog.stopRunning());
    }

    public void stopMovingDogs() {
        dogsOnArea.forEach(dog -> dog.stopRunning());
    }

    private final Area area;
    private final ArrayList<Juh> sheepsOnArea;
    private final ArrayList<Kutya> dogsOnArea;
}