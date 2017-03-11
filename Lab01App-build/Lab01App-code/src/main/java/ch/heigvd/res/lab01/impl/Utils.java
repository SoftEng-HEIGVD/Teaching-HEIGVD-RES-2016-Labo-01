package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Olivier Liechti
 * @author Mathias Gilson
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    /**
     * This method looks for the next new line separators (\r, \n, \r\n) to extract
     * the next line in the string passed in arguments.
     *
     * @param lines a string that may contain 0, 1 or more lines
     * @return an array with 2 elements; the first element is the next line with
     * the line separator, the second element is the remaining text. If the argument does not
     * contain any line separator, then the first element is an empty string.
     */
    public static String[] getNextLine(String lines) {
        //I use regex Pattern to find delimiters
        Matcher matcher = Pattern.compile("\r|\r\n|\n").matcher(lines);

        //If one of the delimiters is found
        if(matcher.find()) {

            // We need to skip two chars if the delimiter is "\r\n"
            int shift = lines.indexOf("\r\n") < 0 ? 1 : 2;

            //Returns an Array with parsed string
            return new String[]{lines.substring(0,matcher.start() + shift),lines.substring((matcher.start() + shift))};
        }
        else {
            return new String[]{"",lines};
        }
    }

}
