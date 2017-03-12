package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Ludovic Richard
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
    String[] s = new String[2];
    int index;
    boolean doubleSeparator = false;
    if((index = lines.indexOf("\r\n")) != -1) {
      doubleSeparator = true;
    } else if((index = lines.indexOf("\r")) != -1) {
    } else if((index = lines.indexOf("\n")) != -1) {
    }

    if(index == -1) {
      s[0] = "";
      s[1] = lines;
      return s;
    }

    if(doubleSeparator) {
      s[0] = lines.substring(0, index + 2);
      s[1] = lines.substring(index + 2);
    }
    else {
      s[0] = lines.substring(0, index + 1);
      s[1] = lines.substring(index + 1);
    }

    return s;
  }

}
