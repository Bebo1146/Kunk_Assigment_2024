package Farm.Placers;

import java.util.Objects;

public class IndexPair {
    public final int X;
    public final int Y;

    public IndexPair(int x, int y) {
        X = x;
        Y = y;
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
        return (X == indexPair.X) && (Y == indexPair.Y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(X, Y);
    }
}