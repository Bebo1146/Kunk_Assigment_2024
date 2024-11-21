package Area.Placers;

import java.util.ArrayList;

import Area.Area;
import Area.Field;
import Area.IndexPair;
import FieldEntities.Juh;

public class SheepPlacer {
    public SheepPlacer(Area area, int amountOfSheeps, int waitTimeMilliseconds){
        this.area = area;
        this.amountOfSheeps = amountOfSheeps;
        this.waitTimeMilliseconds = waitTimeMilliseconds;
        idCounter = 0;
        areSheepsPlaced = false; 
        placedSheeps = new ArrayList<>();
    }

    public ArrayList<Juh> getPlacedSheeps(){
        if(!areSheepsPlaced){
            throw new UnsupportedOperationException("Juhok még nincsenek elhelyezve.");
        }

        return placedSheeps;
    }

    public Area placeSheeps(){
        areSheepsPlaced = true;
        
        SheepPlaces sheepPlaces = new SheepPlaces(area);

        IndexPair sheepsStart = sheepPlaces.getStart();
        IndexPair sheepsFinish = sheepPlaces.getFinish();

        addSheepsTo(area, sheepsStart, sheepsFinish);

        return area;
    }

    private void addSheepsTo(Area area, IndexPair sheepsStart, IndexPair sheepsFinish){
        int x = sheepsStart.getX();
        while(x < sheepsFinish.getX()){
            addSheepsVerticalyTo(area, sheepsStart.getY(), sheepsFinish.getY(), x);
            x++;
        }
    }

    private void addSheepsVerticalyTo(Area area, int fromY, int toY, int x){
        int counter = fromY;
        while(counter < toY && amountOfSheeps > 0){
            IndexPair position = new IndexPair(x, counter);
            
            Juh juh = new Juh(createIdFrom(), area, position, waitTimeMilliseconds);
            placedSheeps.add(juh);

            Field field = new Field(position.getX(), position.getY(), juh, true);

            area.addTypeTo(position, field);
            counter++;
            amountOfSheeps--;
            idCounter++;
        }
    }

    private char createIdFrom(){
        return (char) ('A' + idCounter);
    }

    private Area area;
    private int amountOfSheeps;
    private int waitTimeMilliseconds;
    private int idCounter;
    private boolean areSheepsPlaced;
    private ArrayList<Juh> placedSheeps;
}