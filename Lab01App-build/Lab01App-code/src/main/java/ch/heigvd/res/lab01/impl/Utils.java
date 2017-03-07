package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti 
 * Modified by Lara Chauffoureaux on 07.03.2017
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

        String[] result = new String[2];

        final int LENGTH = lines.length();
        int firstCRPosition = lines.indexOf("\r");
        int firstLFPosition = lines.indexOf("\n");

        // position on the first line separator
        int separatorIndex;

        // i use xor trick to separate cases when we need min or max
        if (firstCRPosition == -1 ^ firstLFPosition == -1) {

            separatorIndex = Math.max(firstCRPosition, firstLFPosition);
        } else {

            separatorIndex = Math.min(firstCRPosition, firstLFPosition);
        }

        // if there is a new line character     
        if (separatorIndex != -1) {

            // case when \r\n follow
            if (separatorIndex != LENGTH - 1 && lines.charAt(separatorIndex) == '\r'
                    && lines.charAt(separatorIndex + 1) == '\n') {

                result[0] = lines.substring(0, separatorIndex + 2);
                result[1] = lines.substring(separatorIndex + 2, LENGTH);
            } else {

                result[0] = lines.substring(0, separatorIndex + 1);
                result[1] = lines.substring(separatorIndex + 1, LENGTH);
            }
        } // if there's no new line character
        else {

            result[0] = "";
            result[1] = lines;
        }

        return result;
    }
}
