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
        
        // result array
        String result[] = {"", lines};

        // find line separator position
        int startPositionOfSecondLine = -1;
        for (int i = 0; i < lines.length(); i++) {
            
            // check if current char is a line separator
            if (lines.charAt(i) == '\r') {
                startPositionOfSecondLine = i + 1;
                if (i + 1 < lines.length() && lines.charAt(i + 1) == '\n')
                    startPositionOfSecondLine++;
                break;
            }
            else if (lines.charAt(i) == '\n') {
                startPositionOfSecondLine = i + 1;
                break;
            }
        }

        // fill result array
        if (startPositionOfSecondLine != -1) {
            result[0] = lines.substring(0, startPositionOfSecondLine);
            result[1] = lines.substring(startPositionOfSecondLine);
        }
        
        return result;
    }
}