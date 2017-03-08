package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Abass Mahdavi
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
      String[] result = new String[2];
      int r = lines.indexOf('\r');
      int n = lines.indexOf('\n');
      
      if (n < 0 & r < 0){
          result[1] = lines;
          result[0] = "";
      } else if (n >= 0 & (n < r | r < 0 )){
          result[0] = lines.substring(0, n + 1);
          
          if (n < lines.length()){
              result[1] = lines.substring(n + 1);
          } else {
            result[1] = "";
          }
      } else {    // r > 0 & (r < n | n < 0)
          if (r == lines.length() - 1){
              result[0] = lines;
              result[1] = "";
          } else if (lines.charAt(r+1) != '\n'){  // r < lines.length()
              result[0] = lines.substring(0, r + 1);
              result[1] = lines.substring(r + 1);
          } else {  //lines.charAt(r+1) == '\n'
              if (r + 1 == lines.length()){
                  result[0] = lines;
                  result[1] = "";
              } else {
                  result[0] = lines.substring(0, r + 2);
                  result[1] = lines.substring(r + 2);
              }
          }

      }
      return result;
  }

}
