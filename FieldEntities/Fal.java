package FieldEntities;

public class Fal extends Thread implements FieldEntity {
    public Fal() {
        this.setName("#");
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public EntityType getType() {
        return EntityType.WALL;
    }
}
