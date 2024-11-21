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

    public ArrayList<Kutya> getPlacedDogs(){
        if(!areDogsPlaced){
            throw new UnsupportedOperationException("Kutyák még nincsenek elhelyezve.");
        }

        return placedDogs;
    }

    public Area placeDogs(){
        areDogsPlaced = true;
        
        List<IndexPair> dogPositions = new ArrayList<>();
        for(int i = 0; i < amountOfDogs; i++){
            int randomX = randomNumberBetweenOneAnd(area.getLength() - Adjustment.VALUE);
            int randomY = randomNumberBetweenOneAnd(area.getWidth() - Adjustment.VALUE);
            
            while (area.getField(randomX, randomY).isSheepArea() || isDogThere(randomX, randomY, dogPositions)) 
            {
                randomX = randomNumberBetweenOneAnd(area.getLength()  - Adjustment.VALUE);
                randomY = randomNumberBetweenOneAnd(area.getWidth()  - Adjustment.VALUE);
            }

            IndexPair indexPair = new IndexPair(randomX, randomY);
            dogPositions.add(indexPair);

            Kutya kutya = new Kutya(i, area, indexPair, waitTimeMilliseconds);
            placedDogs.add(kutya);
            Field field = new Field(indexPair.getX(), indexPair.getY(), kutya,  false);
            area.addTypeTo(indexPair, field);
        }

        return area;
    }

    private int randomNumberBetweenOneAnd(int max){
        Random rand = new Random();
        return rand.nextInt(max - 2) + 2;
    }

    private boolean isDogThere(int row, int col, List<IndexPair> dogPositions) {
        IndexPair position = new IndexPair(row, col);

        return dogPositions.contains(position);
    }

    private final Area area;
    private final int amountOfDogs;
    private final int waitTimeMilliseconds;
    private boolean areDogsPlaced;
    private final ArrayList<Kutya> placedDogs;
}