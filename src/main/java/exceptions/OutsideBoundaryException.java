package exceptions;

/**
 * Created by amitkumar on 3/6/17.
 */
public class OutsideBoundaryException extends RuntimeException {
    public OutsideBoundaryException(String message) {
        super(message);
    }
}
