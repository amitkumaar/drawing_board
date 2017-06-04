package shapes;

import container.internal.GraphicsContext;

/**
 * Created by amitkumar on 3/6/17.
 */
public interface Shape {
    void draw(GraphicsContext graphicsContext);

    boolean insideGraphicsContextBoundary(GraphicsContext graphicsContext);
}
