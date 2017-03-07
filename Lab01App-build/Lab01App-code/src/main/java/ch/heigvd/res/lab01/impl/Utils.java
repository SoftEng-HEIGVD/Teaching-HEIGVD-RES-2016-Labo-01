package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 * @author Lucas Elisei (@faku99)
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
        // We define the separators.
        // WARNING: The order is important here ("\r\n" should be detected before
        // '\r' or '\n').
        String[] separators = {"\r\n", "\n", "\r"};

        // Max value.
        int minIndex = lines.length();

        // Default return values.
        String leftString = "";
        String rightString = lines;

        for (String separator : separators) {
            // We search for the first separator.
            int index = lines.indexOf(separator);

            // If there is one, we update `minIndex`, `leftString` and `rightString`.
            if (index >= 0 && index < minIndex) {
                minIndex = index;

                leftString = lines.substring(0, minIndex + separator.length());
                rightString = lines.substring(minIndex + separator.length());
            }
        }

        return new String[]{leftString, rightString};
    }

}
