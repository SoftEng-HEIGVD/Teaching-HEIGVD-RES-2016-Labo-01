package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti
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
        String[] as = new String[2];

        // if string was created by a windows system
        int i = lines.indexOf("\r\n");
        if(i != -1) {
            as[0] = lines.substring(0, i + 2);
            as[1] = lines.substring(i + 2);

            return as;
        }

        // if string was created by a Unix system
        i = lines.indexOf("\n");
        if(i != -1) {
            as[0] = lines.substring(0, i + 1);
            as[1] = lines.substring(i + 1);

            return as;
        }

        // if string was created by an OSX system
        i = lines.indexOf("\r");
        if(i != -1) {
            as[0] = lines.substring(0, i + 1);
            as[1] = lines.substring(i + 1);

            return as;
        }

        // contains only 1 line without a new line char of some kind present
        as[0] = "";
        as[1] = lines;

        return as;
    }

}
