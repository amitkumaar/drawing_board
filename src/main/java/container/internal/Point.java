package container.internal;

import java.util.Arrays;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

/**
 * Created by amitkumar on 3/6/17.
 */
public class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "container.internal.Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public static Point of(int x, int y) {
        return new Point(x, y);
    }

    public Set<Point> locateNeighbours() {
        return Arrays.stream(Directions.values()).map(this::apply).collect(toSet());
    }

    private Point apply(Directions direction) {
        return Point.of(getX() + direction.getDeltaX(), getY() + direction.getDeltaY());

    }

    public boolean isInsideCanvas(GraphicsContext graphicsContext) {
        return (getX() <= graphicsContext.getWidth() && getX() >= 1)
                && (getY() >= 1 && getY() <= graphicsContext.getHeight());

    }
}
