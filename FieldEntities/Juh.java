package FieldEntities;

import java.util.ArrayList;
import java.util.List;

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
        Field current = area.GetField(position.GetX(), position.GetY());
        Field up = area.GetField(position.GetX(), position.GetY() + 1);
        Field down = area.GetField(position.GetX(), position.GetY() - 1);
        Field right = area.GetField(position.GetX() + 1, position.GetY());
        Field left = area.GetField(position.GetX() - 1, position.GetY());

        ArrayList<Field> possibleMoves = GetPossibleMoves(up, down, right, left);

        if (possibleMoves.isEmpty()){
            Move();
            return;
        }

        if (up.IsDog() && down.IsEmpty()) {
            if(!movementHandler.TryMove(current, down)){
                Move();
                return;
            }

            position = new IndexPair(down.GetX(), down.GetY());
            return;
        }
        else if(down.IsDog() && up.IsEmpty()){
            if(!movementHandler.TryMove(current, up)){
                Move();
                return;
            }

            position = new IndexPair(up.GetX(), up.GetY());
            return;
        }
        else if(right.IsDog() && left.IsEmpty()){
            if(!movementHandler.TryMove(current, left)){
                Move();
                return;
            }

            position = new IndexPair(left.GetX(), left.GetY());
            return;
        }
        else if(left.IsDog() && right.IsEmpty()){
            if(!movementHandler.TryMove(current, right)){
                Move();
                return;
            }

            position = new IndexPair(right.GetX(), left.GetY());
            return;
        }

        Field filedToMove = movementHelper.GetFieldToMoveFrom(possibleMoves);
            
        if(!movementHandler.TryMove(current, filedToMove)){
            Move();
            return;
        }

        position = new IndexPair(filedToMove.GetX(), filedToMove.GetY());
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