package FieldEntities.Movement;

import Area.Field;
import FieldEntities.EmptyField;

public class MovementHandler {
    public boolean TryMove(Field from, Field to) {
        if(!to.TrySetValue(from.GetValue())){
            return false;
        }
        from.Empty();

        return true;
    }
}
