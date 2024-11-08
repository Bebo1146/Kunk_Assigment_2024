package Area;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import FieldEntities.FieldEntity;
import FieldEntities.EmptyField;
import FieldEntities.EntityType;

public class Field {
    public int GetX(){
        return x;
    }

    public int GetY(){
       return y;
    }

    public FieldEntity GetValue() {
        readLock.lock();
        try {
            return value;
        } finally {
            readLock.unlock();
        }
    }

    public boolean TrySetValue(FieldEntity value) {
        writeLock.lock();
        try {
            if (this.value.GetType() == EntityType.EMPTY) {
                this.value = value;
                return true;
            }
        } finally {
            writeLock.unlock();
        }
        return false;
    }

    public Field(int x, int y, FieldEntity value){
        this.x = x;
        this.y = y;
        this.value = value;
        lock = new ReentrantLock();
        readWriteLock = new ReentrantReadWriteLock();
        writeLock = readWriteLock.writeLock();
        readLock = readWriteLock.readLock();
    }

    public boolean IsGate(){
        return value.GetType() == EntityType.GATE;
    }

    public boolean IsWall(){
        return value.GetType() == EntityType.WALL;
    }

    public boolean IsDog(){
        return value.GetType() == EntityType.DOG;
    }

    public boolean IsSheep(){
        return value.GetType() == EntityType.SHEEP;
    }

    public boolean IsEmpty(){
        return value.GetType() == EntityType.EMPTY;
    }

    public void Empty(){
        value = new EmptyField();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    public int x;
    public int y;
    private FieldEntity value;
    private final Lock lock;
    private final ReadWriteLock readWriteLock;
    private final Lock readLock;
    private final Lock writeLock;
}
