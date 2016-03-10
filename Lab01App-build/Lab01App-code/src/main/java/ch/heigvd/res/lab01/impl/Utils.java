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
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    int index_n = lines.indexOf('\n');
    if(index_n == -1){
       int index_r = lines.indexOf('\r');
       if(index_r == -1){
          return new String[]{"", lines};
       }
       else{
          return new String[]{lines.substring(0, index_r+1), lines.substring(index_r+1, lines.length())};
       }
    }
    else{
       return new String[]{lines.substring(0, index_n+1), lines.substring(index_n+1, lines.length())};
    }
  }

}
