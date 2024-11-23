package FieldEntities.Movement;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Random;

import Area.Field;

public class MovementHelper {
    public Optional<Field> getFieldToMoveFrom(ArrayList<Field> possibleMoves) {
        if(possibleMoves.size() > 0){
            Random rand = new Random();
            int randomIndex = rand.nextInt(possibleMoves.size());

            return Optional.of(possibleMoves.get(randomIndex));
        }

        return Optional.empty();
    }
}
