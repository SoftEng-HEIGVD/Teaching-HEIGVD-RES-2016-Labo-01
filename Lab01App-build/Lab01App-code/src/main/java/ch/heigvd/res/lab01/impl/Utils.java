/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : Labo-01
 File         : Utils.java
 Author       : Olivier Liechti, Guillaume Serneels
 Date         : 13.03.2016
 But          : String processing utilitaries to implement the quote fetching 
                and treatment application
 -----------------------------------------------------------------------------------
 */
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

        String[] linesArray = new String[2];
        //To store the new line feed occurence's index
        int index = 0;
        
        boolean newLineOccurence = false;
        //Check for windows style new line feed
        if (lines.contains("\r\n")) {
            index = lines.indexOf("\r\n") + 1;
            newLineOccurence = true;
        //Check for MacOSX style new line feed
        } else if (lines.contains("\r")) {
            index = lines.indexOf("\r");
            newLineOccurence = true;
        //Check for Linux style new line feed            
        } else if (lines.contains("\n")) {
            index = lines.indexOf("\n");
            newLineOccurence = true;
        }
        //If the string contains a new line character, 
        //we fill the array accordingly
        if (newLineOccurence) {
            linesArray[0] = lines.substring(0, index + 1);
            linesArray[1] = lines.substring(index + 1);
        } else {
            linesArray[0] = "";
            linesArray[1] = lines;
        }
        return linesArray;
    }
}                

  


