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

        String[] delims = {"(?<=\r\n)", "(?<=\n)", "(?<=\r)"}; // Prefix ?<= store the delimiter in the string whose has been splited
        String[] parts; // Result tab

    /* Split with diffrents delimiters*/
        for (String delim : delims) {
            parts = lines.split(delim, 2); // the second argument : limit of the split

            if (parts.length == 2) { //parts size must be 2 if we split correctly
                return parts;
            }
        }
        /* Case so split */
        parts = new String[]{"", lines};

        return parts;

    }

}
