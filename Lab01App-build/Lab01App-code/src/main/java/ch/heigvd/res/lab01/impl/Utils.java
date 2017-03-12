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
    public static String[] getNextLine(String lines)
    {
        /*
        We should be using System.lineSeparator() to be able to separate
        the string in several lines independently of the system, but the unit tests
        don't allow us to do so.
        */
        
        int indexLinux = lines.indexOf('\n');
        int indexMac = lines.indexOf('\r');
        int realIndex = -1;
        
        if (indexLinux > -1)
        {
            if (indexMac > -1)
            {
                if (indexMac > indexLinux)
                {
                    realIndex = indexLinux;
                }
                else if (indexMac == indexLinux - 1)
                {
                    realIndex = indexLinux;
                }
                else
                {
                    realIndex = indexMac;
                }
            }
            else
            {
                realIndex = indexLinux;
            }
        }
        else if (indexMac > -1)
        {
            realIndex = indexMac;
        }
        
        String[] result = new String[2];
        if (realIndex > -1)
        {
            result[0] = lines.substring(0, realIndex + 1);
            result[1] = lines.substring(realIndex + 1);
        }
        else
        {
            result[0] = "";
            result[1] = lines;
        }
        
        return result;
    }

}
