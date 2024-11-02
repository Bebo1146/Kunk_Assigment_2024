package Area;

public class Field {
    public Field(int x, int y, FieldType value){
        this.x = x;
        this.y = y;
        this.value = value;
    }

    public boolean IsGate(){
        return value == FieldType.GATE;
    }

    public boolean IsDog(){
        return value == FieldType.DOG;
    }

    @Override
    public String toString() {
        switch(value) {
            case WALL:
                return "#";
            case GATE:
                return " ";
            case DOG:
                return "D";
            case SHEEP:
                return "S";
            default:
                return ".";
        }
    }

    public int x;
    public int y;
    private FieldType value;
}
