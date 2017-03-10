package ch.heigvd.res.lab01.impl;

import java.util.ArrayList;
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
    String[] array = new String[2];
    String first_elem = "";
    String second_elem = "";
    boolean windows = false;

    int i = 0;
    while(i < lines.length() && lines.charAt(i) != '\r' && lines.charAt(i) != '\n') {
      first_elem += lines.charAt(i);
      i++;
    }

    if(i + 1 < lines.length() && lines.charAt(i) == '\r' && lines.charAt(i + 1) == '\n') {
      windows = true;
    }

    if(i == lines.length()) {
      first_elem = "";
      second_elem = lines;
    } else if(i != 0) {
      first_elem += lines.charAt(i);
      if(windows) {
        i++;
        first_elem += lines.charAt(i);
      }
      i++;
    }
    array[0] = first_elem;

    while(i < lines.length()) {
      second_elem += lines.charAt(i);
      i++;
    }

    array[0] = first_elem;
    array[1] = second_elem;

    return array;
  }

}
