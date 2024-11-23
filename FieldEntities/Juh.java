package FieldEntities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import Area.Area;
import Area.Field;
import Area.IndexPair;
import Area.Observing.GateReachedEvent;
import Area.Observing.IGateReachedListener;
import FieldEntities.Movement.MovementHandler;
import FieldEntities.Movement.MovementHelper;

public class Juh extends Thread implements FieldEntity {
    public Juh(char id, Area area, IndexPair position, int waitTimeMilliseconds) {
        this.setName(Character.toString(id));
        this.area = area;
        this.waitTimeMilliseconds = waitTimeMilliseconds;
        this.position = position;
        this.movementHandler = new MovementHandler();
        this.movementHelper = new MovementHelper();
        this.running = true;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public void stopRunning() {
        running = false;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(waitTimeMilliseconds);
                Move();

                Field current = area.getField(position.getX(), position.getY());

                if (current.isGate()) {
                    triggerGateReachedEvent();
                    running = false;

                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public EntityType getType() {
        return EntityType.SHEEP;
    }

    public void addGateReachedListener(IGateReachedListener listener) {
        listeners.add(listener);
    }

    public void removeGateReachedListener(IGateReachedListener listener) {
        listeners.remove(listener);
    }

    private void Move() {
        Field current = area.getField(position.getX(), position.getY());
        Field up = area.getField(position.getX(), position.getY() + 1);
        Field down = area.getField(position.getX(), position.getY() - 1);
        Field right = area.getField(position.getX() + 1, position.getY());
        Field left = area.getField(position.getX() - 1, position.getY());

        ArrayList<Field> possibleMoves = getPossibleMoves(up, down, right, left);
        if (possibleMoves.isEmpty()){
            return;
        }

        ArrayList<DirectionPair> pairs = new ArrayList<DirectionPair>() {{
            add(new DirectionPair(up, down));
            add(new DirectionPair(down, up));
            add(new DirectionPair(right, left));
            add(new DirectionPair(left, right));
        }};
            
        Optional<Field> newPos = tryGetPositionIncaseDogsFrom(pairs, current);
        if(newPos.isPresent()){
            position = new IndexPair(newPos.get().getX(), newPos.get().getY());
            return;
        }

        Optional<Field> filedToMove = movementHelper.getFieldToMoveFrom(possibleMoves);
            
        if(filedToMove.isPresent()){
            if(!movementHandler.tryMove(current, filedToMove.get()))
            {
                return;
            }

            position = new IndexPair(filedToMove.get().getX(), filedToMove.get().getY());   
        }
    }

    private ArrayList<Field> getPossibleMoves(Field up, Field down, Field right, Field left) {
        ArrayList<Field> possibleMoves = new ArrayList<>();
        if (up.isEmpty() || up.isGate()) {
            possibleMoves.add(up);   
        }
        if (down.isEmpty() || down.isGate()) {
            possibleMoves.add(down);
        }
        if (right.isEmpty() || right.isGate()) {
            possibleMoves.add(right);   
        }
        if (left.isEmpty() || left.isGate()) {
            possibleMoves.add(left);   
        }

        return possibleMoves;
    }

    private Optional<Field> tryGetPositionIncaseDogsFrom(ArrayList<DirectionPair> pairs, Field current)
    {
        List<Field> filteredFields = pairs.stream()
            .filter(fieldPair -> {
                Field direction = fieldPair.getDirection();
                Field opposite = fieldPair.getOppositeDirection();
        
                if (direction.isDog() && opposite.isEmpty()) {
                    return movementHandler.tryMove(current, opposite);
                }

                return false;
            })
            .map(DirectionPair::getOppositeDirection)
            .collect(Collectors.toList());
        
        if (filteredFields.isEmpty()) {
            return Optional.empty();
        }
        
        Collections.shuffle(filteredFields);
        return Optional.of(filteredFields.get(0));
    }

    private void triggerGateReachedEvent() {
        GateReachedEvent event = new GateReachedEvent(this);
        for (IGateReachedListener listener : listeners) {
            listener.onGateReached(event);
        }
    }

    private Area area;
    private boolean running;
    private int waitTimeMilliseconds;
    private IndexPair position;
    private MovementHandler movementHandler;
    private MovementHelper movementHelper;
    private final List<IGateReachedListener> listeners = new ArrayList<>();
}