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
      
      String[] separators = new String[2];
      int n;
      
      if(lines.indexOf('\n') == -1){
          n = lines.indexOf('\r');
      }
      else{
          n= lines.indexOf('\n');
      }
        separators[0] = lines.substring(0,n+1);
        separators[1] = lines.substring(n+1);
        return separators;
    
    
      //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }
  

}
