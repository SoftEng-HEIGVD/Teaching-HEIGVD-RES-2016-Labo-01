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
  int pos = 0;    //for recovered indexes end line in the string that will be useful to subtract the next line
  
  
  /*
   *  the idea is to seek is an occurrence of the different end of line in the 
   * string using the indexof method that returns the index of the first element
   *  goes into setting if it finds no recurrence it returns -1
   *
   */   
  
  
    if((pos=lines.indexOf("\r\n"))!=- 1 )
        return new String []{lines.substring(0, pos + 2), lines.substring(pos + 2)}; 
    
    
    else if((pos=lines.indexOf("\r"))!=- 1 )
       return new String []{lines.substring(0, pos + 1), lines.substring(pos + 1)};  
    
    
     else if((pos=lines.indexOf("\n"))!=- 1 )
       return new String []{lines.substring(0, pos + 1), lines.substring(pos + 1)}; 
     
     
    
    else
     return new String[] {"", lines};  
         
  }

}
