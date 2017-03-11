package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;


/**
 *
 * @author Olivier Liechti
 * 
 *  Modified by Nathalie Mégevand
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
      
    String[] firstLineAndRest = {"", ""};
    int indexOfSeparator;
    int indexNextLine = 1;
     
    indexNextLine = (lines.contains("\r\n")) ? 2 : 1;
    
    // Taking advantage of Java lazyness by-passing the or operator.
    // Even if there is a complete "\r\n", only the index of the "\r" is needed.
    if ( ((indexOfSeparator = lines.indexOf("\r")) > -1 ) || 
         ((indexOfSeparator = lines.indexOf("\n")) > -1 ) ) {
      
        //
        firstLineAndRest[0] = lines.substring(0, indexOfSeparator + indexNextLine);
        firstLineAndRest[1] = lines.substring(indexOfSeparator + indexNextLine);
        
    }
    else {
        firstLineAndRest[1] = lines;       
    }
 
    return firstLineAndRest;
  }

}
