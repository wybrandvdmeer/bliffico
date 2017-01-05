/**
 * Created by wybrand on 13-12-16.
 */
public class Coordinate {

    private int x,y;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public String toString() {
        return String.format("%d/%d", x, y);
    }
}
