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
     String[] lineByLine = new String[2];
     int ligneSeparator = lines.indexOf('\n');
     // for mac detection
     if (ligneSeparator == -1){
         
         ligneSeparator = lines.indexOf('\r');
     }
     else {
         // for linux or windows detection
        ligneSeparator = lines.indexOf('\n');
  }   
     lineByLine[0] = lines.substring(0,ligneSeparator + 1);
     lineByLine[1] = lines.substring(ligneSeparator + 1);
     return lineByLine;
    }
    
        
}
