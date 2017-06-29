package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Baeriswyl Julien (julien.baeriswyl@heig-vd.ch) [MODIFIED BY, on 2017-03-10]
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
    public static String[] getNextLine (String lines)
    {
        char c;

        // JBL: iterate through string, until end or new line found
        for (int i = 0; i < lines.length(); ++i)
        {
            c = lines.charAt(i);
            if (c == '\n' | c == '\r') // check first new line char
            {
                ++i;
                // JBL: if next char is also a new line char, different from the current one
                if (i < lines.length() && c != lines.charAt(i) && ((c = lines.charAt(i)) == '\n' | c == '\r'))
                {
                    ++i;
                }

                // JBL: return string splitted in two, with newline chars included in first substring.
                return new String[] {
                    lines.substring(0, i),
                    lines.substring(i)
                };
            }
        }

        // JBL: return first string as empty, meaning there is no multiple lines.
        return new String[] {"", lines};
    }
}
