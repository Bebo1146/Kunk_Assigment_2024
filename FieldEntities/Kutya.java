package FieldEntities;

import java.util.Random;
import Area.Area;
import Area.Field;

public class Kutya extends Thread implements FieldEntity {
    public Kutya(int id) {
        this.setName(String.valueOf(id));
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public EntityType GetType() {
        return EntityType.DOG;
    }
}
