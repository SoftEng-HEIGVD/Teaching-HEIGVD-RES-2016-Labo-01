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
    String[] elems = new String[2];
    int sepLength = 0;
    
    // Find one of the separator
    int idx = lines.indexOf("\r\n");
    sepLength = 2;
    
    if (idx < 0) {
        sepLength = 1;
        idx = lines.indexOf('\n');
        if (idx < 0) {
            idx = lines.indexOf('\r');
        }
    }
    
    // If no separator found, first element is empty and second contains the input String
    if (idx < 0) {
        elems[0] = "";
        elems[1] = lines;
    } else {
        elems[0] = lines.substring(0, idx + sepLength);
        elems[1] = lines.substring(idx + sepLength);
    }
    
    return elems;
  }

}
