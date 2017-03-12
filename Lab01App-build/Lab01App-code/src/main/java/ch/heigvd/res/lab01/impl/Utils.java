package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    /**
     * This method looks for the next new line separators (\r, \n, \r\n) to
     * extract the next line in the string passed in arguments.
     *
     * @param lines a string that may contain 0, 1 or more lines
     * @return an array with 2 elements; the first element is the next line with
     * the line separator, the second element is the remaining text. If the
     * argument does not contain any line separator, then the first element is
     * an empty string.
     */
    public static String[] getNextLine(String lines) {
        String[] delims = {"\r\n", "\r", "\n"};
        String[] res = new String[2];
        
        int cursor;
        for (String d : delims) {
            cursor = lines.indexOf(d);

            if (cursor > 0) {
                res[0] = lines.substring(0, cursor + d.length());
                res[1] = lines.substring(cursor + d.length(), lines.length());
                return res;
            }
        }
        
        // If not found
        res[0] = "";
        res[1] = lines;
        return res;
    }

}
