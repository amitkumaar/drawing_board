package shapes;

import container.internal.GraphicsContext;
import container.internal.Point;

/**
 * Created by amitkumar on 3/6/17.
 */
public class Rectangle implements Shape {

    private final Line top;
    private final Line right;
    private final Line bottom;
    private final Line left;

    private Rectangle(Line top, Line right, Line bottom, Line left) {
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        this.left = left;
    }

    public static Rectangle create(Point upperLeft, Point lowerRight) {
        Line top = Line.of(upperLeft, new Point(lowerRight.getX(), upperLeft.getY()));
        Line bottom = Line.of(Point.of(upperLeft.getX(), lowerRight.getY())
                , lowerRight);
        Line right = Line.of(Point.of(lowerRight.getX(), upperLeft.getY()), lowerRight);
        Line left = Line.of(upperLeft, Point.of(upperLeft.getX(), lowerRight.getY()));
        return new Rectangle(top, right, bottom, left);

    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawLine(this.top);
        graphicsContext.drawLine(this.bottom);
        graphicsContext.drawLine(this.left);
        graphicsContext.drawLine(this.right);

    }

    @Override
    public boolean insideGraphicsContextBoundary(GraphicsContext graphicsContext) {
        return top.insideGraphicsContextBoundary(graphicsContext)
                && right.insideGraphicsContextBoundary(graphicsContext);
    }
}
