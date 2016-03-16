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
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    String[] result = new String[2];
    String firstElement = "";

    for (int i = 0; i < lines.length(); i++) {
      firstElement += lines.charAt(i);

      if (lines.charAt(i) == '\r') {
        // check if we are not at the end of the lines
        if ((i + 1) != lines.length() && lines.charAt(i + 1) == '\n') {
          firstElement += lines.charAt(i + 1);

          result[1] = lines.substring(i + 2, lines.length());
        }
        else {
          result[1] = lines.substring(i + 1, lines.length());
        }

        result[0] = firstElement;

        return result;
      }
      else {
        if (lines.charAt(i) == '\n') {
          result[0] = firstElement;
          result[1] = lines.substring(i + 1, lines.length());

          return result;
        }
      }
    }

    // if we do not have find a line separators
    result[0] = "";
    result[1] = lines;

    return result;
  }
}
