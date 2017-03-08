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
        String[] tab = {"", ""};

        //Explore all of the char
        for (int i = 0; i < lines.length(); i++) {
            char curChar = lines.charAt(i);
            tab[0] += curChar;

            if (curChar == '\n') {
                tab[1] = lines.substring(i + 1);
                break;
            } else if (curChar == '\r' && i < lines.length() - 1 && lines.charAt(i + 1) != '\n') {
                tab[1] = lines.substring(i + 1);
                break;
            }
        }

        //It Should Return A Line Only If There Is A New Line Character At The End
        if (tab[0].charAt(tab[0].length() - 1) != '\n'
                && tab[0].charAt(tab[0].length() - 1) != '\r') {
            tab[1] = tab[0];
            tab[0] = "";
        }

        return tab;
    }

}
