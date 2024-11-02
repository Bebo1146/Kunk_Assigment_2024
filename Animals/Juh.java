package Animals;

import java.util.Random;
import Area.Area;
import Area.Field;
import Farm.Farm;

public class Juh extends Thread {
    public Juh(char id, Area area, Field position, int waitTimeMilliseconds) {
        random = new Random();
        this.setName(Character.toString(id));
        
        this.area = area;
        this.waitTimeMilliseconds = waitTimeMilliseconds;
        this.position = position;
    }

    @Override
    public String toString() {
        return this.getName();
    }

    // @Override
    // public void run() {
    //     while (true) {
    //         try {
    //             Thread.sleep(waitTimeMilliseconds);
    //             move();
    //             if (farm.isGate(x, y)) {
    //                 System.out.println("Sheep escaped!");
    //                 break;
    //             }
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    // }

    // private void move() {
    //     int[] direction = getDirection();
    //     int newX = x + direction[0];
    //     int newY = y + direction[1];

    //     if (farm.isValidMove(newX, newY)) {
    //         x = newX;
    //         y = newY;
    //     }
    // }

    // private int[] getDirection() {
    //     int[] direction = new int[2];

    //     if (farm.isDogNearby(x, y)) {
    //         direction = farm.getOppositeDirection(x, y);
    //     } else {
    //         direction[0] = random.nextInt(3) - 1; // -1, 0, or 1
    //         direction[1] = random.nextInt(3) - 1; // -1, 0, or 1
    //     }

    //     if (direction[0] == 0 && direction[1] == 0) {
    //         direction[random.nextInt(2)] = random.nextBoolean() ? 1 : -1;
    //     }

    //     return direction;
    // }

    private Area area;
    private int waitTimeMilliseconds;
    private Field position;
    private Random random;
}