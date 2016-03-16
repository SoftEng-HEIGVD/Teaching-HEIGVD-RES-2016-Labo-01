/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : 1
 File         : Utils.java
 Author       : Antoine Drabble
 Date         : 10.03.2016
 -----------------------------------------------------------------------------------
*/
package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * Allows reading line by line a string using the following line 
 * separators : \n, \r and \r\n.
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
        String[] ret = {"",""};
        int lastLineReturnPosition = 0;
        boolean newLineFound = false;
        int index = 0;
        // Check every characters in lines
        for(int i = 0; i < lines.length(); i++){
            // For \r\n
            if(lines.charAt(i) == '\r' && i + 1 < lines.length() && lines.charAt(i + 1) == '\n'){
                ret[index++] = lines.substring(lastLineReturnPosition, i + 2);
                lastLineReturnPosition = i + 2;
                newLineFound = true;
                break;
            }
            // For \n and \r
            else if(lines.charAt(i) == '\n' || lines.charAt(i) == '\r'){
                ret[index++] = lines.substring(lastLineReturnPosition, i + 1);
                lastLineReturnPosition = i + 1;
                newLineFound = true;
                break;
            }
        }
        // Add the last part of lines
        if(lastLineReturnPosition < lines.length()){
            if(!newLineFound){
                index++;
            }
            ret[index] = lines.substring(lastLineReturnPosition);
        }
        return ret;
    }
}
