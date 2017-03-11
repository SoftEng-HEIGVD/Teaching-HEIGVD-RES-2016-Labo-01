package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Sydney Hauke
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
    int indexOfLF = lines.indexOf('\n'); // Index of Line Feed
    int indexOfCR = lines.indexOf('\r'); // Index of Carriage Return

    /* If we found '\r\n', split after '\n'. Otherwise, split after '\n' or '\r'. */
    int splitIndex = indexOfLF > indexOfCR ? indexOfLF:indexOfCR;

    return new String[]{lines.substring(0, splitIndex+1), lines.substring(splitIndex+1)};
  }

}
