package FieldEntities;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Area.Adjustment;
import Area.Area;
import Area.Field;
import Area.GateReachedEvent;
import Area.GateReachedListener;
import Area.IndexPair;
import FieldEntities.Movement.MovementHandler;

public class Juh extends Thread implements FieldEntity {
    public Juh(char id, Area area, IndexPair position, int waitTimeMilliseconds) {
        this.setName(Character.toString(id));
        this.area = area;
        this.waitTimeMilliseconds = waitTimeMilliseconds;
        this.position = position;
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

    public void AddGateReachedListener(GateReachedListener listener) {
        listeners.add(listener);
    }

    public void RemoveGateReachedListener(GateReachedListener listener) {
        listeners.remove(listener);
    }

    private void Move() {
        Field current = area.GetField(position.GetX(), position.GetY());
        Field up = area.GetField(position.GetX(), position.GetY() + 1);
        Field down = area.GetField(position.GetX(), position.GetY() - 1);
        Field right = area.GetField(position.GetX() + 1, position.GetY());
        Field left = area.GetField(position.GetX() - 1, position.GetY());

        ArrayList<Field> possibleMoves = getPossibleMoves(up, down, right, left);

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
        }
        else if(down.IsDog() && up.IsEmpty()){
            if(!movementHandler.TryMove(current, up)){
                Move();
                return;
            }

            position = new IndexPair(up.GetX(), up.GetY());
        }
        else if(right.IsDog() && left.IsEmpty()){
            if(!movementHandler.TryMove(current, left)){
                Move();
                return;
            }

            position = new IndexPair(left.GetX(), left.GetY());
        }
        else if(left.IsDog() && right.IsEmpty()){
            if(!movementHandler.TryMove(current, right)){
                Move();
                return;
            }

            position = new IndexPair(right.GetX(), left.GetY());
        }

        Field filedToMove = GetFieldToMoveFrom(possibleMoves);
        
        if(!movementHandler.TryMove(current, filedToMove)){
            Move();
            return;
        }

        position = new IndexPair(filedToMove.GetX(), filedToMove.GetY());
    }

    private ArrayList<Field> getPossibleMoves(Field up, Field down, Field right, Field left) {
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

        return possibleMoves;
    }

    private Field GetFieldToMoveFrom(ArrayList<Field> possibleMoves) {
        Random rand = new Random();
        int randomIndex = rand.nextInt(possibleMoves.size());

        return possibleMoves.get(randomIndex);
    }

    private void TriggerGateReachedEvent() {
        GateReachedEvent event = new GateReachedEvent(this);
        for (GateReachedListener listener : listeners) {
            listener.OnGateReached(event);
        }
    }

    private Area area;
    private int waitTimeMilliseconds;
    private IndexPair position;
    private MovementHandler movementHandler;
    private final List<GateReachedListener> listeners = new ArrayList<>();
}
