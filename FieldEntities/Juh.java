package FieldEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

                Field current = area.GetField(position.GetX(), position.GetY());

                if (current.IsGate()) {
                    TriggerGateReachedEvent();
                    break;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public EntityType GetType() {
        return EntityType.SHEEP;
    }

    public void AddGateReachedListener(IGateReachedListener listener) {
        listeners.add(listener);
    }

    public void RemoveGateReachedListener(IGateReachedListener listener) {
        listeners.remove(listener);
    }

    private void Move() {
        try {
            Field current = area.GetField(position.GetX(), position.GetY());
            Field up = area.GetField(position.GetX(), position.GetY() + 1);
            Field down = area.GetField(position.GetX(), position.GetY() - 1);
            Field right = area.GetField(position.GetX() + 1, position.GetY());
            Field left = area.GetField(position.GetX() - 1, position.GetY());

            ArrayList<Field> possibleMoves = GetPossibleMoves(up, down, right, left);
            if (possibleMoves.isEmpty()){
                return;
            }

            ArrayList<DirectionPair> pairs = new ArrayList<DirectionPair>() {{
                add(new DirectionPair(up, down));
                add(new DirectionPair(down, up));
                add(new DirectionPair(right, left));
                add(new DirectionPair(left, right));
            }};
            
            Optional<Field> newPos = TryGetPositionIncaseDogsFrom(pairs, current);
            if(newPos.isPresent()){
                position = new IndexPair(newPos.get().GetX(), newPos.get().GetY());
                return;
            }

            Field filedToMove = movementHelper.GetFieldToMoveFrom(possibleMoves);
            
            if(!movementHandler.TryMove(current, filedToMove))
            {
                return;
            }

            position = new IndexPair(filedToMove.GetX(), filedToMove.GetY());   
        } catch (Exception e) {
            System.out.println(e.getMessage());

            System.exit(MAX_PRIORITY);
        }
    }

    private ArrayList<Field> GetPossibleMoves(Field up, Field down, Field right, Field left) {
        ArrayList<Field> possibleMoves = new ArrayList<>();
        if (up.IsEmpty() || up.IsGate()) {
            possibleMoves.add(up);   
        }
        if (down.IsEmpty() || down.IsGate()) {
            possibleMoves.add(down);
        }
        if (right.IsEmpty() || right.IsGate()) {
            possibleMoves.add(right);   
        }
        if (left.IsEmpty() || left.IsGate()) {
            possibleMoves.add(left);   
        }

        return possibleMoves;
    }

    private Optional<Field> TryGetPositionIncaseDogsFrom(ArrayList<DirectionPair> pairs, Field current)
    {
        return pairs.stream()
        .filter(fieldPair -> {
            Field direction = fieldPair.GetDirection();
            Field opposite = fieldPair.GetOppositeDirection();

            if (direction.IsDog() && opposite.IsEmpty()) {
                if(!movementHandler.TryMove(current, opposite)){
                    return false;
                }
    
                return true;
            }

            return false;   
        })
        .map(DirectionPair::GetOppositeDirection)
        .findFirst(); // maybe get random
    }


    private void TriggerGateReachedEvent() {
        GateReachedEvent event = new GateReachedEvent(this);
        for (IGateReachedListener listener : listeners) {
            listener.OnGateReached(event);
        }
    }

    private Area area;
    private int waitTimeMilliseconds;
    private IndexPair position;
    private MovementHandler movementHandler;
    private MovementHelper movementHelper;
    private final List<IGateReachedListener> listeners = new ArrayList<>();
}