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

        int nextLinePos = 0;

        //Explore all of the char
        for (int i = 0; i < lines.length(); i++) {
            char curChar = lines.charAt(i);
            
            //Finding the next line--
            //Windows
            if(i + 1 < lines.length() && curChar == '\r' && lines.charAt(i + 1) == '\n'){
                nextLinePos = i + 1;
                break;
            }
            //Other OS
            if (curChar == '\n' || curChar == '\r') {
                nextLinePos = i;
                break;
            }
        }

        //Result tab
        String[] tab = new String[2];

        if (nextLinePos == 0) { //No new line
            tab[0] = "";
            tab[1] = lines;
        } else { //With new line
            tab[0] = lines.substring(0, nextLinePos + 1);
            tab[1] = lines.substring(nextLinePos + 1);
        }

        return tab;
    }

}
