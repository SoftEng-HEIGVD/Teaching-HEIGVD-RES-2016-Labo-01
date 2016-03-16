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
    int pos;
    String res[] = new String[2];
    
    // \n for linux and \n\r for Windows
    pos = lines.indexOf('\n');
    if(pos == -1)
    {
        // \r for Mac
        pos = lines.indexOf('\r');
    }
    // If there is no line seperator
    if(pos == -1)
    {
        res[0] = "";
        res[1] = lines;
        return res;
    }
    
    //Write the lines in the result array
    res[0] = lines.substring(0, pos+1);
    res[1] = lines.substring(pos+1);
    
    return res;
  }

}
