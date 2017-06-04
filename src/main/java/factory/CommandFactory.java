package factory;

import constants.ValidCommands;
import factory.CommandType;

/**
 * Created by amitkumar on 3/6/17.
 */
public class CommandFactory {
    public static CommandType create(String input) {
        if (input.matches(ValidCommands.QUIT)) {
            return CommandType.QUIT;
        } else if (input.matches(ValidCommands.CREATE_CANVAS)) {
            return CommandType.CANVAS;
        } else if (input.matches(ValidCommands.CREATE_LINE)) {
            return CommandType.LINE;
        } else if (input.matches(ValidCommands.CREATE_RECTANGLE)) {
            return CommandType.RECTANGLE;
        } else if (input.matches(ValidCommands.BUCKET_FILL)) {
            return CommandType.BUCKET_FILL;
        }
        return CommandType.UNKNOWN;
    }

}
