package FieldEntities;

public class EmptyField extends Thread implements FieldEntity {
    public EmptyField() {
        this.setName(".");
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public EntityType GetType() {
        return EntityType.EMPTY;
    }
}
