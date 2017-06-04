package com.drawing;

import console.ConsoleWriter;
import container.Canvas;
import container.internal.Point;
import exceptions.OutsideBoundaryException;
import shapes.Line;
import shapes.Rectangle;

/**
 * Created by amitkumar on 3/6/17.
 */
public class NonInteractiveRunner {
    public static void main(String[] args) throws OutsideBoundaryException {

        Canvas canvas = Canvas.of(20, 4).withConsoleWriter(new ConsoleWriter());
        canvas.draw();
        Line line = Line.of(new Point(1, 2), new Point(6, 2));
        canvas.add(line);
        Line line1 = Line.of(new Point(6, 3), new Point(6, 4));
        canvas.add(line1);
        line = Line.of(new Point(7, 3), new Point(7, 4));
        canvas.add(line);

        Rectangle rectangle = Rectangle.create(Point.of(14, 1), Point.of(18, 3));
        canvas.add(rectangle);

        canvas.fillCanvas(Point.of(16, 2), 'o');

    }

}
