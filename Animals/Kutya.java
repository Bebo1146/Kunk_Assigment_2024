package Animals;

import java.util.Random;
import Area.Area;
import Area.Field;

public class Kutya extends Thread {
    public Kutya(int id) {
        this.setName(String.valueOf(id));
    }
}
