package Area;

import java.util.Objects;

public class IndexPair {
    public int GetX() {
        return x;
    }

    public int GetY() {
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
        return (x == indexPair.GetX()) && (y == indexPair.GetY());
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public final int x;
    public final int y;
}