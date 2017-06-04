package container;

import console.ConsoleWriter;
import container.internal.Dimension;
import container.internal.GraphicsContext;
import container.internal.Point;
import exceptions.OutsideBoundaryException;
import shapes.Shape;

import java.util.Arrays;

/**
 * Created by amitkumar on 3/6/17.
 */
public class Canvas {

    public Dimension getSize() {
        return size;
    }

    private final Dimension size;
    private final GraphicsContext graphicsContext;
    private ConsoleWriter consoleWriter;

    private Canvas(int width, int height) {
        size = Dimension.of(width + 2, height + 2);
        graphicsContext = GraphicsContext.create(size.getHeight(), size.getWidth());
        graphicsContext.drawCanvas(getSize());
    }

    public static Canvas of(int width, int height) {
        if (width <= 0 || height <= 0)
            throw new IllegalArgumentException("dimension can not be negative {" + " width " + width
                    + " height " + height + " } ");
        return new Canvas(width, height);
    }

    public void fillCanvas(Point point, char color) throws OutsideBoundaryException {
        if (!point.isInsideCanvas(graphicsContext)) {
            throw new OutsideBoundaryException(String.format(" %s is outside defined canvas boundary %s", point, graphicsContext));
        }
        graphicsContext.bucketFill(point, color);
        draw();
    }


    public void add(Shape shape) {
        if (!shape.insideGraphicsContextBoundary(graphicsContext)) {
            throw new OutsideBoundaryException(String.format(" %s is outside defined canvas boundary", shape));
        }
        shape.draw(graphicsContext);
        draw();
    }


    public void draw() {
        consoleWriter.getOutStream().print(System.lineSeparator());
        Arrays.stream(graphicsContext.getDisplay()).forEach(consoleWriter.getOutStream()::println);
    }

    public void dispose() {
        graphicsContext.dispose();
    }

    public Canvas withConsoleWriter(ConsoleWriter consoleWriter) {
        this.consoleWriter = consoleWriter;
        return this;
    }

    @Override
    public String toString() {
        return "container.Canvas{" +
                "size=" + size +
                ", graphicsContext=" + graphicsContext +
                '}';
    }
}
