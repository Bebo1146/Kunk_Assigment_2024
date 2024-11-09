package FieldEntities.Movement;

import java.util.ArrayList;
import java.util.Random;

import Area.Field;

public class MovementHelper {
    public Field GetFieldToMoveFrom(ArrayList<Field> possibleMoves) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(possibleMoves.size());

        return possibleMoves.get(randomIndex);
    }
}
