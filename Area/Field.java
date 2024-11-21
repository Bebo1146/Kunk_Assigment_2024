package Area;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import FieldEntities.FieldEntity;
import FieldEntities.EmptyField;
import FieldEntities.EntityType;

public class Field {
    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean isSheepArea(){
        return isSheepArea;
    }

    public FieldEntity getValue() {
        readLock.lock();
        try {
            return value;
        } finally {
            readLock.unlock();
        }
    }

    public boolean trySetValue(FieldEntity value) {
        writeLock.lock();
        try {
            if (this.value.getType() == EntityType.EMPTY) {
                this.value = value;
                return true;
            }
            if (this.value.getType() == EntityType.GATE) {
                return true;
            }
        } finally {
            writeLock.unlock();
        }
        
        return false;
    }

    public Field(int x, int y, FieldEntity value, boolean isSheepArea){
        this.x = x;
        this.y = y;
        this.value = value;
        this.isSheepArea = isSheepArea;
        readWriteLock = new ReentrantReadWriteLock();
        writeLock = readWriteLock.writeLock();
        readLock = readWriteLock.readLock();
    }

    public boolean isGate(){
        return value.getType() == EntityType.GATE;
    }

    public boolean isWall(){
        return value.getType() == EntityType.WALL;
    }

    public boolean isDog(){
        return value.getType() == EntityType.DOG;
    }

    public boolean isSheep(){
        return value.getType() == EntityType.SHEEP;
    }

    public boolean isEmpty(){
        return value.getType() == EntityType.EMPTY;
    }

    public void empty(){
        value = new EmptyField();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public final int x;
    public final int y;
    private final boolean isSheepArea;
    private FieldEntity value;
    private final ReadWriteLock readWriteLock;
    private final Lock readLock;
    private final Lock writeLock;
}
