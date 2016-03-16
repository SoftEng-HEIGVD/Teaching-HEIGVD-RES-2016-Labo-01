package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
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
      String[] res = new String[] {"", ""};
      // go though every character of the string
    for(int i = 0; i < lines.length(); i++)
    {
        // find \n or \r
        if(lines.charAt(i) == '\n' || lines.charAt(i) == '\r')
        {
            // split the string lines into two
            // look for \n after \r to split in the right place
            if(lines.length() > i+1 && lines.charAt(i+1) == '\n')
            {
                res[0] += lines.substring(0,i+2);
                res[1] += lines.substring(i+2);
                return res;
            }
            else
            {
                res[0] += lines.substring(0,i+1);
                res[1] += lines.substring(i+1);
                return res;
            }
        }
    }
      return new String[] {"", lines};
  }

}
