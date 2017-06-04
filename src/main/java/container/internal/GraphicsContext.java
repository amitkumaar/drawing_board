package container.internal;

import constants.DisplayChars;
import shapes.Line;

import java.util.stream.IntStream;

/**
 * Created by amitkumar on 3/6/17.
 */
public class GraphicsContext {

    private char[][] display;

    public char[][] getDisplay() {
        return display;
    }

    public int getWidth() {
        return display[0].length - 2;
    }

    public int getHeight() {
        return display.length - 2;
    }

    private GraphicsContext(int row, int column) {
        display = new char[row][column];
    }

    public static GraphicsContext create(int row, int column) {
        return new GraphicsContext(row, column);
    }


    public void dispose() {
        display = null;
    }


    public void drawCanvas(Dimension size) {
        for (int row = 0; row < size.getHeight(); row++) {
            for (int column = 0; column < size.getWidth(); column++) {
                if (row == 0 || row == size.getHeight() - 1)
                    display[row][column] = DisplayChars.HORIZONTAL_BOUNDARY_CHAR;
                else if (column == 0 || column == size.getWidth() - 1)
                    display[row][column] = DisplayChars.VERTICAL_BOUNDARY_CHAR;
                else display[row][column] = DisplayChars.EMPTY_CELL_CHAR;

            }
        }
        //Arrays.stream(display).forEach(System.out::println);
    }

    public void drawLine(Line line) {
        if (line.xAlligned()) {
            drawHorizontalLine(line);
        } else if (line.yAlligned()) {
            drawVerticalLine(line);
        }
    }

    private void drawVerticalLine(Line line) {
        if (line.drawReverse()) {
            IntStream.rangeClosed(line.getEndPoint().getY(), line.getStartPoint().getY())
                    .forEach(row -> {
                        display[row][line.getStartPoint().getX()] = DisplayChars.LINE_CHAR;
                    });
        } else {
            IntStream.rangeClosed(line.getStartPoint().getY(), line.getEndPoint().getY())
                    .forEach(row -> {
                        display[row][line.getStartPoint().getX()] = DisplayChars.LINE_CHAR;
                    });
        }
    }

    private void drawHorizontalLine(Line line) {
        if (line.drawReverse()) {
            IntStream.rangeClosed(line.getEndPoint().getX(), line.getStartPoint().getX())
                    .forEach(column -> {
                        display[line.getStartPoint().getY()][column] = DisplayChars.LINE_CHAR;
                    });

        } else {
            IntStream.rangeClosed(line.getStartPoint().getX(), line.getEndPoint().getX())
                    .forEach(column -> {
                        display[line.getStartPoint().getY()][column] = DisplayChars.LINE_CHAR;
                    });
        }
    }

    public void bucketFill(Point point, char Color) {
        if (this.isInsideCanvas(point) && isValidForFill(point, Color)) {
            display[point.getY()][point.getX()] = Color;
            point.locateNeighbours().forEach(point1 -> bucketFill(point1, Color));
        }
    }

    private boolean isValidForFill(Point point, char color) {
        return display[point.getY()][point.getX()] != color
                && (display[point.getY()][point.getX()] == DisplayChars.EMPTY_CELL_CHAR
                || display[point.getY()][point.getX()] != DisplayChars.LINE_CHAR);
    }

    public boolean isInsideCanvas(Point point) {
        return (point.getX() <= getWidth() && point.getX() >= 1)
                && (point.getY() >= 1 && point.getY() <= getHeight());

    }

    @Override
    public String toString() {
        return "container.internal.GraphicsContext{" +
                "width =" + getWidth() +
                " height =" + getHeight() +
                '}';
    }
}
