package FieldEntities.Movement;

import Area.Field;

public class MovementHandler {
    public boolean tryMove(Field from, Field to) {
        if(from.isEmpty()){
            return false;
        }

        if(!to.trySetValue(from.getValue())){
            return false;
        }

        from.empty();

        return true;
    }
}
