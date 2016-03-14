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
        final int arraySize = 2;
        int idxBackR = 0; // index of \r
        int idxBackN = 0; // it works for \n or \r\n
        String[] elems = new String[arraySize];

        idxBackN = lines.indexOf("\n");
        if (idxBackN == -1) {
            idxBackR = lines.indexOf("\r");
        }

        if (idxBackN == -1 && idxBackR == -1) {
            elems[0] = "";
            elems[1] = lines;
            return elems;
        } else if (idxBackN != -1) {
            elems[0] = lines.substring(0, idxBackN + 1);
            if (lines.length() < idxBackN + 1) {
                elems[1] = "";
            } else {
                elems[1] = lines.substring(idxBackN + 1);
            }
        } else {
            elems[0] = lines.substring(0, idxBackR + 1);
            if (lines.length() < idxBackR + 1) {
                elems[1] = "";
            } else {
                elems[1] = lines.substring(idxBackR + 1);
            }
        }

        return elems;
    }

}
