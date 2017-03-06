package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
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
        char[] standardDelimiters = new char[]{'\r', '\n'};
        String  currentDelimiter = "";
        int index = 0;

        for(char delim : standardDelimiters) {
            int i = lines.indexOf(delim);
            if(i != -1) {
                currentDelimiter += delim;
                index = i;
            }
        }

        return new String[] { lines.substring(0, index + 1), lines.substring(index + 1) };
    }

}
