package FieldEntities;

import java.util.ArrayList;
import java.util.stream.Collectors;

import Area.Area;
import Area.Field;
import Area.IndexPair;
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
        this.running = true;
    }

    public void stopRunning() {
        running = false;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(waitTimeMilliseconds);
                move();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public EntityType getType() {
        return EntityType.DOG;
    }

    private void move() {
        Field current = area.getField(position.getX(), position.getY());
        Field up = area.getField(position.getX(), position.getY() + 1);
        Field down = area.getField(position.getX(), position.getY() - 1);
        Field right = area.getField(position.getX() + 1, position.getY());
        Field left = area.getField(position.getX() - 1, position.getY());

        ArrayList<Field> possibleMoves = getPossibleMoves(up, down, right, left);

        Field filedToMove = movementHelper.getFieldToMoveFrom(possibleMoves);

        if(!movementHandler.tryMove(current, filedToMove)){
            return;
        }

        position = new IndexPair(filedToMove.getX(), filedToMove.getY());
    }

    private ArrayList<Field> getPossibleMoves(Field up, Field down, Field right, Field left) {
        ArrayList<Field> possibleMoves = new ArrayList<>();
        if (up.isEmpty()) {
            possibleMoves.add(up);   
        }
        if (down.isEmpty()) {
            possibleMoves.add(down);
        }
        if (right.isEmpty()) {
            possibleMoves.add(right);   
        }
        if (left.isEmpty()) {
            possibleMoves.add(left);   
        }

        return removeSheepAreaFrom(possibleMoves);
    }

    private ArrayList<Field> removeSheepAreaFrom(ArrayList<Field> possibleMoves) {
        return possibleMoves.stream()
        .filter(field -> !field.isSheepArea())
        .collect(Collectors.toCollection(ArrayList::new));
    }

    private IndexPair position;
    private boolean running;
    private final Area area;
    private final int waitTimeMilliseconds;
    private final MovementHelper movementHelper;
    private final MovementHandler movementHandler;
}
