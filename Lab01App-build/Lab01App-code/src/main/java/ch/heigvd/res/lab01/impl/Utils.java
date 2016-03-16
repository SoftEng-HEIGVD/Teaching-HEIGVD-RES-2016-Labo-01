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
     // unfound notif, value return by the class method String.indexOf() 
     // if the char doesn't occur
     final int UNFOUND = -1;
     String res[] = new String[2];
     // init of the two strings contained in the tab
     res[0] = "";
     res[1] = "";
     int pos = lines.indexOf('\n'); // for Windows and Unix
    
     if(pos == UNFOUND) 
        pos = lines.indexOf("\r"); // for Mac
     // if no new line characters found, affect the lines and do nothing 
     // for the separator
     if(pos == UNFOUND){
        res[1] = lines;
        return res;
        
     } else{
        res[0] = lines.substring(0, pos + 1); // pos + 1 for the delimitor
        res[1]= lines.substring(pos + 1); // idem
     }
     return res;                     
  }

}
