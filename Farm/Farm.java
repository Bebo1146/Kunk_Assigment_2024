package Farm;

import Area.Area;
import Area.FieldType;

public class Farm {
    public Farm(Area area) {
        myArea = area;
    }

    public void DisplayArea() {
        myArea.Display();
    }

    public void AddSheepsToArea(int from, int till) {
        
    }

    // public int[] getOppositeDirection(int x, int y) {
    //     int[] direction = {0, 0};

    //     if (isDogNearby(x - 1, y)) {
    //         direction[0] = 1;
    //     } else if (isDogNearby(x + 1, y)) {
    //         direction[0] = -1;
    //     }

    //     if (isDogNearby(x, y - 1)) {
    //         direction[1] = 1;
    //     } else if (isDogNearby(x, y + 1)) {
    //         direction[1] = -1;
    //     }

    //     return direction;
    // }

    public Area myArea;
}