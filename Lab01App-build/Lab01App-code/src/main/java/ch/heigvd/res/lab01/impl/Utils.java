package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author David Truan
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
        // the array containing the nextline characters for different OS
        String[] charNextLine = {"\r\n", "\n", "\r"};

        int firstOccurence = lines.length();

        // we search the nearest nextline char
        for (String token : charNextLine) {
            if (lines.indexOf(token) != -1 && lines.indexOf(token) < firstOccurence) {
                firstOccurence = lines.indexOf(token);
                if (token == "\r\n") {
                    firstOccurence++;
                    break;
                }
            }
        }
        // if there was a nextline char in the line we return the substring as 
        // asked, else we return the ("", lines) array
        if (firstOccurence != lines.length()) {
            return new String[]{lines.substring(0, firstOccurence + 1), lines.substring(firstOccurence + 1)};
        } else {
            return new String[]{"", lines};
        }
    }

}
