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
   
      // Creates the array to return and initialize its elements 
      String[] array = new String[2];
      array[0] = "";
      array[1] = "";
      
      // Else if the lines contains \r, it splits the line at the \r
      if (lines.contains("\r\n")) {
          array[0] = lines.split("\r\n")[0] + "\r\n";
      }
      
      // If the lines contains \n, it splits the line at the \n
      else if (lines.contains("\n")) {
          array[0] = lines.split("\n")[0] + "\n";
      }
      
      // Else if the lines contains \r\n, it splits the line at the \r\n
      else if (lines.contains("\r")) {
          array[0] = lines.split("\r")[0] + "\r";
      }
      
      array[1] = lines.substring(array[0].length());
      
      return array;
  }

}
