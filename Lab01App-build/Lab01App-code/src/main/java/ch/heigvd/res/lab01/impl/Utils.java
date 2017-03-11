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
   * @author Ludovic Delafontaine
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
      
      // Where to store the result
      String[] result = new String[2];
      
      String endingChar = null;
      
      // Windows ending line characters
      if (lines.contains("\r\n")) {
          endingChar = "\r\n";
      
      // MacOS ending line characters
      } else if (lines.contains("\r")) {
          endingChar = "\r";
          
      // Linux ending line characters
      } else if (lines.contains("\n")) {
          endingChar = "\n";
          
      // No ending line characters found
      } else {
          result[0] = "";
          result[1] = lines;
          return result;
      }
      
      // Get the position of the ending chars
      int index = lines.indexOf(endingChar) + endingChar.length();
      
      // Save the result
      result[0] = lines.substring(0, index);
      result[1] = lines.substring(index);
      
      return result;
  }

}
