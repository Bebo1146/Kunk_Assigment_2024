package FieldEntities;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.stream.Collectors;

import Area.Area;
import Area.Field;
import Area.IndexPair;
import Area.Placers.SheepPlaces;
import FieldEntities.Movement.MovementHandler;
import FieldEntities.Movement.MovementHelper;

public class Kutya extends Thread implements FieldEntity {
    public Kutya(int id, Area area, IndexPair position, int waitTimeMilliseconds) {
        this.setName(String.valueOf(id));
        this.area = area;
        this.position = position;
        this.waitTimeMilliseconds = waitTimeMilliseconds;
        this.movementHelper = new MovementHelper();
        this.movementHandler = new MovementHandler();
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(waitTimeMilliseconds);
                Move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public EntityType GetType() {
        return EntityType.DOG;
    }

    private void Move() {
        Field current = area.GetField(position.GetX(), position.GetY());
        Field up = area.GetField(position.GetX(), position.GetY() + 1);
        Field down = area.GetField(position.GetX(), position.GetY() - 1);
        Field right = area.GetField(position.GetX() + 1, position.GetY());
        Field left = area.GetField(position.GetX() - 1, position.GetY());

        ArrayList<Field> possibleMoves = GetPossibleMoves(up, down, right, left);

        Field filedToMove = movementHelper.GetFieldToMoveFrom(possibleMoves);

        if(!movementHandler.TryMove(current, filedToMove)){
            Move();
            return;
        }

        position = new IndexPair(filedToMove.GetX(), filedToMove.GetY());
    }

    public ArrayList<Field> GetPossibleMoves(Field up, Field down, Field right, Field left) {
        ArrayList<Field> possibleMoves = new ArrayList<>();
        if (up.IsEmpty()) {
            possibleMoves.add(up);   
        }
        if (down.IsEmpty()) {
            possibleMoves.add(down);
        }
        if (right.IsEmpty()) {
            possibleMoves.add(right);   
        }
        if (left.IsEmpty()) {
            possibleMoves.add(left);   
        }

        return RemoveSheepAreaFrom(possibleMoves);
    }

    private ArrayList<Field> RemoveSheepAreaFrom(ArrayList<Field> possibleMoves) {
        SheepPlaces sheepPlaces = new SheepPlaces(area);
    
        IndexPair sheepsStart = sheepPlaces.GetStart();
        IndexPair sheepsFinish = sheepPlaces.GetFinish();

        return possibleMoves.stream()
        .filter(field -> !(field.GetX() >= sheepsStart.GetX() && field.GetX() <= sheepsFinish.GetX()
                     && field.GetY() >= sheepsStart.GetY() && field.GetY() <= sheepsFinish.GetY()))
        .collect(Collectors.toCollection(ArrayList::new));
    }

    private IndexPair position;
    private final Area area;
    private final int waitTimeMilliseconds;
    private final MovementHelper movementHelper;
    private final MovementHandler movementHandler;
}
