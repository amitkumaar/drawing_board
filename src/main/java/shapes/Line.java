package shapes;

import container.internal.GraphicsContext;
import container.internal.Point;

/**
 * Created by amitkumar on 3/6/17.
 */
public class Line implements Shape {

    private final Point startPoint;
    private final Point endPoint;

    private Line(Point startPoint, Point endPoint) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
    }

    public static Line of(Point startPoint, Point endPoint) {
        return new Line(startPoint, endPoint);
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.drawLine(this);

    }

    @Override
    public boolean insideGraphicsContextBoundary(GraphicsContext graphicsContext) {
        return (startPoint.isInsideCanvas(graphicsContext) && endPoint.isInsideCanvas(graphicsContext));
    }

    public boolean xAlligned() {
        return startPoint.getX() != endPoint.getX() && startPoint.getY() == endPoint.getY();

    }

    public boolean yAlligned() {
        return startPoint.getY() != endPoint.getY() && startPoint.getX() == endPoint.getX();
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public String toString() {
        return "shapes.Line{" +
                "startPoint=" + startPoint +
                ", endPoint=" + endPoint +
                '}';
    }

    public boolean drawReverse() {
        return (startPoint.getX() > endPoint.getX())
                || (startPoint.getY() > endPoint.getY());
    }

}
