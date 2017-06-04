package com.drawing;

import console.ConsoleReader;
import console.ConsoleWriter;
import container.Canvas;
import container.internal.Point;
import exceptions.OutsideBoundaryException;
import factory.CommandFactory;
import factory.CommandType;
import shapes.Shape;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * Created by amitkumar on 3/6/17.
 */
public class InteractiveDrawingBoard {
    private Canvas canvas = null;
    private ConsoleReader consoleReader;
    private ConsoleWriter consoleWriter;

    public static void main(String[] args) {
        InteractiveDrawingBoard runner = new InteractiveDrawingBoard(new ConsoleReader(), new ConsoleWriter());
        printHelp();
        runner.run();
    }

    public InteractiveDrawingBoard(ConsoleReader consoleReader, ConsoleWriter consoleWriter) {
        this.consoleReader = consoleReader;
        this.consoleWriter = consoleWriter;
    }

    public void run() {
        while (true) {
            String input = consoleReader.readCommand();
            try {
                if (!parseInputAndExecuteCommand(input)) {
                    break;
                }
            } catch (IllegalArgumentException | OutsideBoundaryException | IllegalStateException ex) {
                consoleWriter.getOutStream().println("invalid input: '" + ex.getMessage() + " '");
            }
        }
    }

    protected boolean parseInputAndExecuteCommand(String input) throws OutsideBoundaryException {
        CommandType commandType = CommandFactory.create(input);
        if (commandType == CommandType.QUIT) {
            return false;
        } else if (commandType == CommandType.UNKNOWN) {
            throw new IllegalArgumentException(String.format("invalid input %s", input));
        } else if (commandType == CommandType.CANVAS) {
            if (canvas != null) {
                canvas.dispose();
            }
            String[] splits = input.split("\\s");
            canvas = Canvas.of(Integer.parseInt(splits[3]), Integer.parseInt(splits[4])).withConsoleWriter(consoleWriter);
            canvas.draw();
        } else if (commandType == CommandType.BUCKET_FILL) {
            if (canvas == null) {
                throw new IllegalStateException(String.format("please create canvas to %s ", commandType));
            }
            String[] splits = input.split("\\s");
            Point point = Point.of(Integer.parseInt(splits[3]), Integer.parseInt(splits[4]));
            canvas.fillCanvas(point, splits[5].charAt(0));

        } else {
            if (canvas == null) {
                throw new IllegalStateException(String.format("please create canvas to draw  %s ", commandType));
            }
            Consumer<Shape> composed = (shape) -> canvas.add(shape);
            Function<String, Shape> shapeFunction = commandType::create;
            composed.accept(shapeFunction.apply(input));
        }
        return true;
    }

    private static void printHelp() {
        System.out.println(System.lineSeparator() + "manual:" + System.lineSeparator() +
                "-------------------------------------------------------------------------------" + System.lineSeparator() +
                "'enter command: C w h'         - create a new canvas of width w and height h " + System.lineSeparator() +
                "'enter command: L x1 y1 x2 y2' - create a new line from (x1, y1) to (x2, y2) " + System.lineSeparator() +
                "'enter command: R x1 y1 x2 y2' - creates a new rectangle whose upper left corner (x1, y1) " + System.lineSeparator() +
                " 		                            and bottom right corner (x2, y2)." + System.lineSeparator() +
                "'enter command: B x y c'       - fill the entire area connected to (x,y) with \"colour\" c" + System.lineSeparator() +
                "'enter command: Q'             - quit the program." + System.lineSeparator() +
                "-------------------------------------------------------------------------------" + System.lineSeparator());
    }


}
