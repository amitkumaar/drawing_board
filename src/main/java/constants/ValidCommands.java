package constants;

/**
 * Created by amitkumar on 3/6/17.
 */
public class ValidCommands {
    public static String CREATE_CANVAS = "\\A"+"enter command:" +"\\s+C\\s+(-?)(\\d+)\\s+(-?)(\\d+)\\s*\\z";
    public static String CREATE_LINE = "\\A"+"enter command:" +"\\s+L\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\z";
    public static String CREATE_RECTANGLE = "\\A"+"enter command:" +"\\s+R\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)\\z";
    public static String BUCKET_FILL = "\\A"+"enter command:" +"\\s+B\\s+(\\d+)\\s+(\\d+)\\s+(\\p{Alnum}){1}\\z";
    public static String QUIT = "\\A"+"enter command:" +"\\s+Q\\z";

}
