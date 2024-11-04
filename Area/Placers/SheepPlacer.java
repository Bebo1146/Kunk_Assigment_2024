package Area.Placers;

import java.util.ArrayList;

import Area.Adjustment;
import Area.Area;
import Area.Field;
import Area.IndexPair;
import FieldEntities.Juh;
import FieldEntities.Kutya;

public class SheepPlacer {
    public ArrayList<Juh> GetPlacedSheeps(){
        if(!areSheepsPlaced){
            throw new UnsupportedOperationException("Sheeps are not placed yet.");
        }

        return placedSheeps;
    }

    public SheepPlacer(Area area, int amountOfSheeps, int waitTimeMilliseconds){
        this.area = area;
        this.amountOfSheeps = amountOfSheeps;
        this.waitTimeMilliseconds = waitTimeMilliseconds;
        idCounter = 0;
        areSheepsPlaced = false; 
        placedSheeps = new ArrayList<>();
    }

    public Area PlaceSheeps(){
        areSheepsPlaced = true;

        int lengthByThree = (area.GetLength() - Adjustment.Value) / 3;
        int widthByThree = (area.GetWidth() - Adjustment.Value) / 3;

        IndexPair sheepsStart = 
            SheepPlaces.GetStartFrom(lengthByThree, widthByThree);

        IndexPair sheepsFinish = 
            SheepPlaces.GetFinishFrom(area, lengthByThree, widthByThree);

        AddSheepsTo(area, sheepsStart, sheepsFinish);

        return area;
    }

    private void AddSheepsTo(Area area, IndexPair sheepsStart, IndexPair sheepsFinish){
        int x = sheepsStart.GetX();
        while(x < sheepsFinish.GetX()){
            AddSheepsVerticalyTo(area, sheepsStart.GetY(), sheepsFinish.GetY(), x);
            x++;
        }
    }

    private void AddSheepsVerticalyTo(Area area, int fromY, int toY, int x){
        int counter = fromY;
        while(counter < toY && amountOfSheeps > 0){
            IndexPair position = new IndexPair(x, counter);
            
            Juh juh = new Juh(CreateIdFrom(), area, position, waitTimeMilliseconds);
            placedSheeps.add(juh);

            Field field = new Field(position.GetX(), position.GetY(), juh);

            area.AddTypeTo(position, field);
            counter++;
            amountOfSheeps--;
            idCounter++;
        }
    }

    private char CreateIdFrom(){
        return (char) ('A' + idCounter);
    }

    private Area area;
    private int amountOfSheeps;
    private int waitTimeMilliseconds;
    private int idCounter;
    private boolean areSheepsPlaced;
    private ArrayList<Juh> placedSheeps;
}