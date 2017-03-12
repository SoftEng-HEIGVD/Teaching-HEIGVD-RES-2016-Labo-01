    package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti, Basile Châtillon
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
    String[] s = new String[2];
    
    // we go throw all character
    boolean delimFound = false;
    int i;
    for(i = 0 ; i < lines.length() ; ++ i){
      char c = lines.charAt(i);
      
      //we found '\r'
      if(c == '\r'){
        // we look after to see if we got a \r\n
        // as a String ends with \0, we do not need to worry about the total length
        if(i+1 < lines.length() && lines.charAt(i+1) == '\n'){
          i++;
        }
        delimFound = true;
        break;
      }
      //we found '\n'
      else if(c == '\n'){
        delimFound = true;
        break;
      }  
    }
    
    // we must now fill the array s
    // if we find a delim, we create 2 substring
    if(delimFound){
        s[0] = lines.substring(0, i+1);
        s[1] = lines.substring(i+1);
    }
    // if we did not foud a delim, we must return an empty string and the lines
    else{
        s[0] = "";
        s[1] = lines;
    }
    return s;
  }

}
