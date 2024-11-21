package FieldEntities;

import Area.Field;

public class DirectionPair {
    public Field getDirection() {
        return direction;
    }
    
    public Field getOppositeDirection() {
        return oppositeDirection;
    }

    public DirectionPair(Field direction, Field oppositeDirection) {
        this.direction = direction;
        this.oppositeDirection = oppositeDirection;   
    }

    private final Field direction;
    private final Field oppositeDirection;
}
