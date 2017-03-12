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
     * @return an array with 2 elements; the first element is the next line with the
     * line separator, the second element is the remaining text. If the argument does
     * not contain any line separator, then the first element is an empty string.
     */
    public static String[] getNextLine(String lines) {
        String[] nextLines = new String[2];
        int i;
        
        // Find the index of an eventual new line separators
        for (i = 0; i < lines.length(); i++){
            char c = lines.charAt(i);
            // Check for \r and \rn
            if (c == '\r'){
                if ((i + 1) < lines.length() && lines.charAt(i + 1) == '\n'){
                    i++;
                }
                break;
            // Check for \n
            } else if (c == '\n'){
                break;
            }
        }
        
        // Fill the string tab with the corresponding lines
        if (i == lines.length()){
            nextLines[0] = "";
            nextLines[1] = lines;
        } else {
            nextLines[0] = lines.substring(0, i + 1);
            nextLines[1] = lines.substring(i + 1);
        }
        return nextLines;
    }

}
