package console;

import java.util.Scanner;

/**
 * Created by amitkumar on 3/6/17.
 */
public class ConsoleReader {
    private final Scanner scanner;

    public ConsoleReader() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        return scanner.nextLine();
    }

}
