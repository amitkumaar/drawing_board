package container.internal;

/**
 * Created by amitkumar on 3/6/17.
 */
public enum Directions {
    NORTH(0, -1),
    SOUTH(0, 1),
    EAST(1, 0),
    WEST(-1, 0),;

    final int deltaX;
    final int deltaY;

    Directions(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
