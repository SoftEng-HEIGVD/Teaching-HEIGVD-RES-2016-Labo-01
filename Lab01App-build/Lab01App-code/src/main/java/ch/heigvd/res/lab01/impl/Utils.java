package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti, Camilo Pineda Serna
 */
public class Utils
{

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
    public static String[] getNextLine(String lines)
    {
        String[] returnArray = new String[2];
        String[] stringsToFind = {"\r", "\n", "\r\n"};
        int indexOfSeparator = lines.length() -1 ;
        int currentIndex = -1;

        // get the minimum index
        for (String currentSeparator : stringsToFind) {
            currentIndex = lines.indexOf(currentSeparator);
            if (currentIndex != -1 && currentIndex <= indexOfSeparator) {
                indexOfSeparator = currentIndex;
            }
        }

        if (indexOfSeparator != -1) {
            returnArray[0] = lines.substring(0, indexOfSeparator + 1);

        } else {
            returnArray[0] = "";
        }
        returnArray[1] = lines.substring(indexOfSeparator + 1);


        return returnArray;
    }

}
