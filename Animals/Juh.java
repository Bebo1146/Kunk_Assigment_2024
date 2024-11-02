package Animals;

import java.util.Random;
import Area.Area;
import Area.Field;

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

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(waitTimeMilliseconds);
                
                System.out.println("Sheep escaped!");
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private Area area;
    private int waitTimeMilliseconds;
    private Field position;
    private Random random;
}