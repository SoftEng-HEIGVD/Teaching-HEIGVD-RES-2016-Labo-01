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
    // find the index of the _first_ separator (if there is one)
    int index = -1;
    String[] separators = {"\r", "\r\n", "\n"}; // the order is important
    for(String separator : separators) {
      int tmp = lines.indexOf(separator);
      if(tmp != -1 && (index == -1 || tmp <= index))
        index = tmp + separator.length(); // we want to keep the separator
    }

    // generate the resulting array
    String[] result = new String[2];
    result[0] = index == -1 ? "" : lines.substring(0, index);
    result[1] = index == -1 ? lines : lines.substring(index);
    return result;
  }

}
