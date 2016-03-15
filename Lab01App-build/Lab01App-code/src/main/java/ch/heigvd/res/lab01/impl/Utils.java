package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Ciani Antony
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
      String result[] = new String[2];
      String newLine1 = "\r";
      String newLine2 = "\n";
      String newLine3 = "\r\n";
      
      int i;
      if((i = lines.indexOf(newLine3)) != -1){
          result[0] = lines.substring(0, i + newLine3.length());
          result[1] = lines.substring(i + newLine3.length(), lines.length());
      }
      else if((i = lines.indexOf(newLine2)) != -1){
          result[0] = lines.substring(0, i + newLine2.length());
          result[1] = lines.substring(i + newLine2.length(), lines.length());
      }
      else if((i = lines.indexOf(newLine1)) != -1){
          result[0] = lines.substring(0, i + newLine1.length());
          result[1] = lines.substring(i + newLine1.length(), lines.length());
      }
      else{
          result[0] = "";
          result[1] = lines;
      }
      
      return result;
  }

}
