package console;

import java.io.PrintStream;

/**
 * Created by amitkumar on 3/6/17.
 */
public class ConsoleWriter {
    private PrintStream outStream;

    public ConsoleWriter() {
        this.outStream = System.out;
    }

    public PrintStream getOutStream() {
        return outStream;
    }
}
