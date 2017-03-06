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
      
      int[] sepInfos = getSeparatorPosition(lines);
      int posSeparator = sepInfos[0];
      int lenSeparator = sepInfos[1];
      
      String arg1, arg2;
      if(posSeparator == -1) {
          arg1 = "";
          arg2 = lines;
      } else {
          arg1 = lines.substring(0, posSeparator + lenSeparator);
          arg2 = lines.substring(posSeparator + lenSeparator, lines.length());
      }
      
      return new String[] {arg1, arg2};
  }
  
  /**
   * Find the first occurence of \r, \n, or \r\n in a string and return
   * its position in that string and its length.
   * 
   * @param s is the String to retrieve from the first separator
   * @return the first element is the position and the second is the length
   * of the separator
   */
  public static int[] getSeparatorPosition(String s) {
      int pos = -1, len = -1, sLen = s.length();
      char[] sArray = s.toCharArray();
      
      for(int i = 0 ; i < sLen ; ++i) {
          
          if(sArray[i] == '\r') {
              pos = i;
              if(i+1 < sLen && sArray[i+1] == '\n') {
                  len = 2;
                  break;
              } else {
                  len = 1;
                  break;
              }
          } else if(sArray[i] == '\n') {
              pos = i;
              len = 1;
              break;
          }
              
      }
      
      return new int[] {pos, len};
  }
}
