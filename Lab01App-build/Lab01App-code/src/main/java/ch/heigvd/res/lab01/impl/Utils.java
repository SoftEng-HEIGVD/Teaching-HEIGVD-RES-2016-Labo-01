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
        String[] tab = new String[2];
        
        /*
         checks which version of end line are used in the current system.
         */
        int i = lines.indexOf('\n');
        int j = lines.indexOf('\r');
        int k = lines.indexOf("\r\n");
        
        if(i == -1 && j == -1 && k == -1) // if no en line symbols
        {
            tab[0] = new String();
            tab[1] = lines;
        }
        else if(i != -1) // if end line symbol is '\n'
        {
            tab[0] = lines.substring(0, i);
            tab[0] += '\n';
            tab[1] = lines.substring(i+1);
        }
        else if(j != -1) // if end line symbol is '\r'
        {
            tab[0] = lines.substring(0, j);
            tab[0] += '\r';
            tab[1] = lines.substring(j+1);
        }
        else // if end line symbol is "\r\n"
        {
            tab[0] = lines.substring(0, k);
            tab[0] += "\r\n";
            tab[1] = lines.substring(k+1);
        }
        return tab;
    }
}
