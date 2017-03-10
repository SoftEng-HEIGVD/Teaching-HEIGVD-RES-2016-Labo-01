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
    String[] ret = new String[2];
    if (!lines.contains("\r") && !lines.contains("\n")) {
      ret[0] = "";
      ret[1] = lines;
    } else {
      int idx = lines.indexOf("\r\n") + 2;
      if(idx == 1) {
        idx = lines.indexOf("\r") + 1;
        if(idx == 0) {
          idx = lines.indexOf("\n") + 1;
        }
      }
      ret[0] = lines.substring(0, idx);
      ret[1] = lines.substring(idx, lines.length());
    }
    return ret;
  }

}
