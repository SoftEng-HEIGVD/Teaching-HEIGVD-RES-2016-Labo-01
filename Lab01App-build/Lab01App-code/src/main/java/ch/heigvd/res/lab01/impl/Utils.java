package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Myszkorowski wojciech
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
      
      
      final int NOTFOUND = -1;
      final String empty = "";
      
      String tab[] = new String[2];
      //initiation empty
      tab[0] = empty;
      tab[1] = empty;
     
      //looking for the first end of line on windows and linux
      int seperator = lines.indexOf('\n');
 
      //looking for the new line on mac
      if(seperator == NOTFOUND) {
          seperator = lines.indexOf("\r");
      } 
      //creation of result
      if(seperator == NOTFOUND) {
         tab[1] = lines;
         return tab;
      } else {
          // position + 1 after the delimitor
          tab[0] = lines.substring(0, seperator + 1);
          tab[1] = lines.substring(seperator + 1); 
      }
      return tab;
  }

}
