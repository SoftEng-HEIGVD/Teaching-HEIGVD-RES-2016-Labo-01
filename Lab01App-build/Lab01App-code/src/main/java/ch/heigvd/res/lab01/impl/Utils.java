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
    String[] ret = {"",lines};
    //it is crucial that strings containing substrings be placed before them: e.g. \r\n must be placed before \r
    //in the array, otherwise the for loop will find \r and not look for \r\n
    String[] separators = {"\r\n", "\r", "\n"};
    int minIndex = -1;
    int currentIndex;
    int separatorIndex = -1;

    for (int i = 0; i < separators.length; i++) {
      currentIndex = lines.indexOf(separators[i]);
      if(minIndex == -1 || currentIndex != -1 && currentIndex < minIndex) {
        minIndex = currentIndex;
        separatorIndex = i;
      }
    }

    if(minIndex == -1)
      return ret;

      ret[0] = lines.substring(0, minIndex + separators[separatorIndex].length());
      ret[1] = lines.substring(minIndex + separators[separatorIndex].length());

    return ret;
  }

}
