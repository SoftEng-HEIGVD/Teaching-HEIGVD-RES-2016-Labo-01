package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;
import java.lang.Math;

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

    //Creation of an array with 2 elements
    String[] newLines = new String[2];

    //Find the firsts 'next new line separators' (indexOf can't have multiple string)
    int findN = lines.indexOf("\n");
    int findR = lines.indexOf("\r");
    int findRN = lines.indexOf("\r\n");

    //Choose the first one
    int findFirst = Math.min(findN,findR);
    findFirst= Math.min(findFirst,findRN);

    //If a character is find
    if(findFirst !=1) {

      newLines[0] = lines.substring(0, findFirst);
      newLines[1] = lines.substring(findFirst + 1);
    }
    else
    {
      newLines[0] = lines;
      newLines[1] = "";
    }
    return newLines;
  }
}
