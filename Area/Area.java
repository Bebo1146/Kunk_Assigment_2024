package Area;

import java.util.Random;

public class Area {
    public int GetLength() {
        return area.length;
    }

    public int GetWidth() {
        return area[0].length; 
    }

    public Area(int length, int width) {
        area = new Field[length][width];
        Init();

        AddWalls();
        AddGates();
    }

    public void Display() {
        for (Field[] row : area) {
            for (Field field : row) {
                System.out.print(field.toString());
            }

            System.out.println();
        }
    }

    public Field GetField(int x, int y) {
        return area[x][y];
    }

    public void AddTypeTo(FieldType type, int x, int y) {
        System.out.println(x + " " + y);

        area[x][y] = new Field(x, y, type);
    }

    private void AddWalls() {
        int length = area.length;
        int width = area[0].length;

        AddWallsTopBottom(length, width);
        AddWallsLeftRight(length, width);
    }

    private void AddWallsTopBottom(int length, int width) {
        for (int i = 0; i < width; i++) {
            area[0][i] = new Field(0, i, FieldType.WALL);
            area[GetLength() - 1][i] = new Field(GetLength() - 1, i, FieldType.WALL);
        }
    }

    private void AddWallsLeftRight(int length, int width) {
        for (int i = 0; i < length; i++) {
            area[i][0] = new Field(i, 0, FieldType.WALL);
            area[i][GetWidth() - 1] = new Field(i, GetWidth() - 1, FieldType.WALL);
        }
    }

    private void AddGates() {
        AddGatesTopBottom();
        AddGatesLeftRight();
    }

    private void AddGatesTopBottom(){
        int topIndex = random.nextInt(GetWidth()- 2) + 1;
        int bottomIndex = random.nextInt(GetWidth()- 2) + 1;

        area[0][topIndex] = new Field(0, topIndex, FieldType.GATE);
        area[GetLength() - 1][bottomIndex] = new Field(GetLength() - 1, bottomIndex, FieldType.GATE);
    }

    private void AddGatesLeftRight(){
        int leftIndex = random.nextInt(GetLength()- 2) + 1;
        int rightIndex = random.nextInt(GetLength()- 2) + 1;

        area[leftIndex][0] = new Field(leftIndex, 0, FieldType.GATE);
        area[rightIndex][GetWidth() - 1] = new Field(rightIndex, GetWidth() - 1, FieldType.GATE);
    }

    private void Init() {
        for (int i = 0; i < area.length; i++) {
            for (int j = 0; j < area[i].length; j++) {
                area[i][j] = new Field(i, j, FieldType.EMPTY);
            }
        }
    }

    public Field[][] area;
    private Random random = new Random();
}
