package container.internal;

/**
 * Created by amitkumar on 3/6/17.
 */
final public class Dimension {
    public final int width;
    public final int height;

    private Dimension(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public static Dimension of(int width, int height) {
        return new Dimension(width, height);
    }
}
