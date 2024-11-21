package Area;

import java.util.Objects;

public class IndexPair {
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public IndexPair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (o == null || getClass() != o.getClass()){
            return false;
        }

        IndexPair indexPair = (IndexPair)o;
        return (x == indexPair.getX()) && (y == indexPair.getY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    private final int x;
    private final int y;
}