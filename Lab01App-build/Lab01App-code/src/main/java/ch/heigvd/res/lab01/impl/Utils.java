package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    private static Pattern nextLine = Pattern.compile("(\r\n|\r|\n)");

    /**
     * This method looks for the next new line separators (\r, \n, \r\n) to extract
     * the next line in the string passed in arguments.
     *
     * @param lines a string that may contain 0, 1 or more lines
     * @return an array with 2 elements; the first element is the next line with
     * the line separator, the second element is the remaining text. If the argument
     * does not
     * contain any line separator, then the first element is an empty string.
     */
    public static String[] getNextLine(String lines) {
        Matcher matcher = nextLine.matcher(lines);
        if (matcher.find()) {
            int index = matcher.end();
            String curLine = lines.substring(0, index);
            String rest = lines.substring(index);
            return new String[] { curLine, rest };
        } else {
            return new String[] { "", lines };
        }
    }

}
