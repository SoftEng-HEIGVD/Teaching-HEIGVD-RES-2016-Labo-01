package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Dany Simo
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

    // declares the Array's string who will contains 2 elements
    String[] stringArray = new String[2];

      // initializes the array 
      stringArray[0] = stringArray[1] = "";
      
      // initializes the index's line
      int indexLine = 0;
      
      // return index of the first occurence of '\n'
      // else return -1 
      indexLine = lines.indexOf("\n");

      // if '\n' didn't find
      if (indexLine == -1) {
          // search the first occurence of '\r'
          // else return -1 
         indexLine = lines.indexOf("\r");
         // if '\r' didn't find
         if (indexLine == -1) {
             //put the text at the second element in the array
            stringArray[1] = lines;
         }
      }

      // if '\n' found store index of the beginning next line
      int length = indexLine + 1;
      // put the next line at the first element in the array
      stringArray[0] = lines.substring(0, length);
      
      // store the remaining text
      if (lines.length() >= length) {
         stringArray[1] = lines.substring(length);
      } 

      return stringArray;
  }

}
