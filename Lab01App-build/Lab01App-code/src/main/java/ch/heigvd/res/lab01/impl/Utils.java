package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Aurelie Levy
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    /**
     * This method looks for the next new line separators (\r, \n, \r\n) to
     * extract the next line in the string passed in arguments.
     * Those separators are for Windows, Unix or MacOs
     *
     * @param lines a string that may contain 0, 1 or more lines
     * @return an array with 2 elements; the first element is the next line with
     * the line separator, the second element is the remaining text. If the
     * argument does not contain any line separator, then the first element is
     * an empty string.
     */
    public static String[] getNextLine(String lines) {
        String[] arrayString = {"", ""};
        boolean quit = false;
        int j = 0;// not to change index of loop. Offset to know where to substring lines
        
        for (int i = 0; i < lines.length(); i++) {
            j = i + 1;

            switch (lines.charAt(i)) {
                case '\r'://Windows or MacOs
                    if ((i + 1) < lines.length() && lines.charAt(i + 1) == '\n') {//windows
                        j += 1;
                    }
                    getNextLinesFillArrayString(j, lines, arrayString);
                    quit = true;
                    break;
                case '\n'://Unix
                    getNextLinesFillArrayString(j, lines, arrayString);
                    quit = true;
                    break;
                default://no separator
                    arrayString[0] = "";
                    arrayString[1] = lines;
                    break;
            }
            
            if (quit) {//arrayString is ok => stop the loop
                break;
            }
        }
        return arrayString;
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    /**
     * to fill the arrayString in the getNextLine method (factorization)
     * @param j index
     * @param lines String to use to fill the array
     * @param arrayString array to fill
     */
    private static void getNextLinesFillArrayString(int j, String lines, String[] arrayString) {
        arrayString[0] = lines.substring(0, j);
        arrayString[1] = lines.substring(j, lines.length());
    }

}
