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
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

        String[] tab = new String[]{"\n", "\r"};
        int index_searched;

        for (String string : tab) {
            if ((index_searched = lines.indexOf(string)) != -1) { // did we reach the end yet?
                return new String[]{  //parsing to keep the part we want, which is the remaining text
                    lines.substring(0, index_searched + string.length()),
                    lines.substring(index_searched + string.length(), lines.length())
                };
            }
        }

        return new String[]{"", lines}; // in case of no line separator, using the empty string 
    }

}
