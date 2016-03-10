package ch.heigvd.res.lab01.impl;

import java.util.logging.Level;
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
        String[] returnTab = new String[2];
        String tmpStr = "";
        int index = 0;
        boolean found = false;
        try{
            for (; index < lines.length(); index++) {
                if (lines.charAt(index) == '\n' || lines.charAt(index) == '\r') {
                    found = true;
                    if (index != lines.length() - 1 && (lines.charAt(index + 1) == '\r' || lines.charAt(index + 1) == '\n')) {
                        index++;
                    }
                    tmpStr = lines.substring(0, index + 1);
                    break;
                }
            }
        }catch(Exception e){
            LOG.log(Level.SEVERE, "Couldn't get next Line!");
        }

        if(!found){
            returnTab[0] = "";
            returnTab[1] = lines;
            return returnTab;
        }

        returnTab[0] = tmpStr;
        returnTab[1] = lines.substring(index + 1);
        return returnTab;
    }

}
