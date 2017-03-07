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
     String[] newLines = new String[2];
     int index;
     
     //find the first occurence of a delimiter
     for(index = 0; index < lines.length(); index++){
        char currChar = lines.charAt(index);
        if(currChar == '\r' || currChar == '\n')
           break;
     }
     
     //check if there is a second delimiter
     if((index + 1 ) < lines.length() && lines.charAt(index + 1) == '\n' && lines.charAt(index) == '\r')
        index++;
     
     //if there is a delim
     if (index < lines.length()){
        newLines[0] = lines.substring(0, index+1);
        newLines[1] = lines.substring(index + 1);
     }
     // if not
     else{
        newLines[0] = "";
        newLines[1] = lines;
     }
     
     return newLines;
  }

}
