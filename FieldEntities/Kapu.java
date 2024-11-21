package FieldEntities;

public class Kapu extends Thread implements FieldEntity {
    public Kapu() {
        this.setName(" ");
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public EntityType getType() {
        return EntityType.GATE;
    }
}
