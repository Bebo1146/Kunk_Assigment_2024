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

    // private boolean isValidCoordinate(int x, int y) {
    //     return x >= 0 && x < myArea.area.length && y >= 0 && y < myArea.area[0].length;
    // }

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