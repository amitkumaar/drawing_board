package factory;

import container.internal.Point;
import shapes.Line;
import shapes.Rectangle;
import shapes.Shape;

/**
 * Created by amitkumar on 3/6/17.
 */
public enum CommandType {
    QUIT {
        @Override
        public Shape create(String input) {
            throw new UnsupportedOperationException();
        }
    },
    CANVAS {
        @Override
        public Shape create(String input) {
            throw new UnsupportedOperationException();
        }
    },
    LINE {
        @Override
        public Shape create(String input) {
            String[] splits = input.split("\\s");
            return Line.of(Point.of(Integer.parseInt(splits[3]), Integer.parseInt(splits[4]))
                    , Point.of(Integer.parseInt(splits[5]), Integer.parseInt(splits[6])));
        }
    },
    RECTANGLE {
        @Override
        public Shape create(String input) {
            String[] splits = input.split("\\s");
            return Rectangle.create(Point.of(Integer.parseInt(splits[3]), Integer.parseInt(splits[4])),
                    Point.of(Integer.parseInt(splits[5]), Integer.parseInt(splits[6])));
        }
    },
    BUCKET_FILL {
        @Override
        public Shape create(String input) {
             throw new UnsupportedOperationException();
        }
    },
    UNKNOWN {
        @Override
        public Shape create(String input) {
            throw new UnsupportedOperationException();
        }
    },;

    public abstract Shape create(String input);
}
