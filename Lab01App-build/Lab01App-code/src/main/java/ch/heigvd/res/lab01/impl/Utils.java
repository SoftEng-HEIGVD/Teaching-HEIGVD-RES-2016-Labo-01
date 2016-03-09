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
        String[] result = new String[2];
        int separator3 = lines.indexOf("\r\n");
        int separator1 = lines.indexOf('\r');
        int separator2 = lines.indexOf('\n');
        int firstSeparator = -1;

        //We shouldn't count the indexes that are equal to -1
        if(separator1 == -1 && separator2 == -1 && separator3 == -1) {
            result[0] = "";
            result[1] = lines;
        } else {
            //If one or two of the endline chars are not in the String, we assign them the maximum value
            if (separator1 == -1)
                separator1 = Integer.MAX_VALUE;
            if (separator2 == -1)
                separator2 = Integer.MAX_VALUE;
            if (separator3 == -1)
                separator3 = Integer.MAX_VALUE;
            separator2 = separator2 < separator3 ? separator2 : separator3;
            firstSeparator = separator1 < separator2 ? separator1 : separator2;

            result[0] = lines.substring(0, firstSeparator + (firstSeparator == separator3 ? 2 : 1));
            result[1] = lines.substring(result[0].length());
        }
        return result;
    }

}
