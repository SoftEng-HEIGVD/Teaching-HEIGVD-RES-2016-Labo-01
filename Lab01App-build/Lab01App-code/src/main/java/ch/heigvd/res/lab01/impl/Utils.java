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

    String[] newLines = new String[2];      //Creation of an array with 2 elements
    int findPositionFirst = lines.length(); // By default, the first position is the size of the string
    int adjustment = 0;

    final String  LF = "\n"; //Line Feed
    final String  CR = "\r"; //Carriage Return
    final String  CRLF = "\r\n"; //CR+LF

    //Find the firsts 'next new line separators' (indexOf can't have multiple string in parameters)
    if(lines.contains(CRLF))
    {
      findPositionFirst = Math.min(findPositionFirst,lines.indexOf(CRLF));
      adjustment = 2;
    }

    else if(lines.contains(LF))
    {
      findPositionFirst = Math.min(findPositionFirst,lines.indexOf(LF));
      adjustment = 1;
    }

    else if(lines.contains(CR))
    {
      findPositionFirst = Math.min(findPositionFirst,lines.indexOf(CR));
      adjustment = 1;
    }

    //If a character is find
    if((findPositionFirst)!=lines.length()){

      //first element is the next line
      newLines[0] = lines.substring(0, findPositionFirst+adjustment);

      //the remaining text
      newLines[1] = lines.substring(findPositionFirst + adjustment);
    }

    //If there are no new lines
    else
    {
      //the first element is an empty string
      newLines[0] = "";

      //The same string
      newLines[1] = lines;
    }

    return newLines;
  }
}
