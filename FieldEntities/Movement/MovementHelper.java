package FieldEntities.Movement;

import java.util.ArrayList;
import java.util.Random;

import Area.Field;

public class MovementHelper {
    public ArrayList<Field> GetPossibleMoves(Field up, Field down, Field right, Field left) {
        ArrayList<Field> possibleMoves = new ArrayList<>();
        if (up.IsEmpty()) {
            possibleMoves.add(up);   
        }
        if (down.IsEmpty()) {
            possibleMoves.add(down);   
        }
        if (right.IsEmpty()) {
            possibleMoves.add(right);   
        }
        if (left.IsEmpty()) {
            possibleMoves.add(left);   
        }

        return possibleMoves;
    }

    public Field GetFieldToMoveFrom(ArrayList<Field> possibleMoves) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(possibleMoves.size());

        return possibleMoves.get(randomIndex);
    }
}
