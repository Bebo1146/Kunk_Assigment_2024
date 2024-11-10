package FieldEntities;

import Area.Field;

public class DirectionPair {
    public Field GetDirection() {
        return direction;
    }
    
    public Field GetOppositeDirection() {
        return oppositeDirection;
    }

    public DirectionPair(Field direction, Field oppositeDirection) {
        this.direction = direction;
        this.oppositeDirection = oppositeDirection;   
    }

    private final Field direction;
    private final Field oppositeDirection;
}
