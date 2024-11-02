package Farm.Placers;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Area.Adjustment;
import Area.Area;
import Area.FieldType;

public class DogPlacer {
    public static Area PlaceTo(Area area, int numberOfDogs){
        int lengthByThree = area.GetLength() / 3;
        int widthByThree = area.GetWidth() / 3;

        IndexPair sheepsStart = 
            SheepPlaces.GetStartFrom(lengthByThree, widthByThree);
        IndexPair sheepsFinish = 
            SheepPlaces.GetFinishFrom(area, lengthByThree, widthByThree);
        
        List<IndexPair> dogPositions = new ArrayList<>();
        for(int i = 0; i < numberOfDogs; i++){
            int randomX = RandomNumberBetweenOneAnd(area.GetLength() - Adjustment.Value);
            int randomY = RandomNumberBetweenOneAnd(area.GetWidth() - Adjustment.Value);
            while (isSheepArea(randomX, randomY, sheepsStart, sheepsFinish) 
                || isDogThere(randomX, randomY, dogPositions)) {
                randomX = RandomNumberBetweenOneAnd(area.GetLength()  - Adjustment.Value);
                randomY = RandomNumberBetweenOneAnd(area.GetWidth()  - Adjustment.Value);
            }

            dogPositions.add(new IndexPair(randomX, randomY));
            area.AddTypeTo(FieldType.DOG, randomX, randomY);
        }

        return area;
    }

    private static int RandomNumberBetweenOneAnd(int max){
        Random rand = new Random();
        return rand.nextInt(max - 2) + 2;
    }

    private static boolean isSheepArea(int row, int col, IndexPair sheepsStart, IndexPair sheepsFinish) {
        return row >= sheepsStart.X && row <= sheepsFinish.X
                && col >= sheepsStart.Y && col <= sheepsFinish.Y;
    }

    private static boolean isDogThere(int row, int col, List<IndexPair> dogPositions) {
        IndexPair position = new IndexPair(row, col);

        return dogPositions.contains(position);
    }
}