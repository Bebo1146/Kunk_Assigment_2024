package Area.Placers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Area.Adjustment;
import Area.Area;
import Area.Field;
import Area.IndexPair;
import FieldEntities.Kutya;

public class DogPlacer {
    public DogPlacer(Area area, int amountOfDogs, int waitTimeMilliseconds){
        this.area = area;
        this.amountOfDogs = amountOfDogs;
        this.waitTimeMilliseconds = waitTimeMilliseconds;
        areDogsPlaced = false; 
        placedDogs = new ArrayList<>();
    }

    public ArrayList<Kutya> GetPlacedDogs(){
        if(!areDogsPlaced){
            throw new UnsupportedOperationException("Dogs are not placed yet.");
        }

        System.out.println(placedDogs.size());

        return placedDogs;
    }

    public Area PlaceDogs(){
        areDogsPlaced = true;
        
        SheepPlaces sheepPlaces = new SheepPlaces(area);

        IndexPair sheepsStart = sheepPlaces.GetStart();
        IndexPair sheepsFinish = sheepPlaces.GetFinish();
        
        List<IndexPair> dogPositions = new ArrayList<>();
        for(int i = 0; i < amountOfDogs; i++){
            int randomX = RandomNumberBetweenOneAnd(area.GetLength() - Adjustment.Value);
            int randomY = RandomNumberBetweenOneAnd(area.GetWidth() - Adjustment.Value);
            while (isSheepArea(randomX, randomY, sheepsStart, sheepsFinish) 
                || isDogThere(randomX, randomY, dogPositions)) {
                randomX = RandomNumberBetweenOneAnd(area.GetLength()  - Adjustment.Value);
                randomY = RandomNumberBetweenOneAnd(area.GetWidth()  - Adjustment.Value);
            }

            IndexPair indexPair = new IndexPair(randomX, randomY);
            dogPositions.add(indexPair);

            Kutya kutya = new Kutya(i, area, indexPair, waitTimeMilliseconds);
            placedDogs.add(kutya);
            Field field = new Field(indexPair.GetX(), indexPair.GetY(), kutya);
            area.AddTypeTo(indexPair, field);
        }

        return area;
    }

    private int RandomNumberBetweenOneAnd(int max){
        Random rand = new Random();
        return rand.nextInt(max - 2) + 2;
    }

    private boolean isSheepArea(int row, int col, IndexPair sheepsStart, IndexPair sheepsFinish) {
        return row >= sheepsStart.GetX() && row <= sheepsFinish.GetX()
                && col >= sheepsStart.GetY() && col <= sheepsFinish.GetY();
    }

    private boolean isDogThere(int row, int col, List<IndexPair> dogPositions) {
        IndexPair position = new IndexPair(row, col);

        return dogPositions.contains(position);
    }

    private Area area;
    private int amountOfDogs;
    private int waitTimeMilliseconds;
    private boolean areDogsPlaced;
    private ArrayList<Kutya> placedDogs;
}