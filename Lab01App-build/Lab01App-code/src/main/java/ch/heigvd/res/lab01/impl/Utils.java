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
      //We assume here that one system will not mix-and-match line seperators,
      //ie. will not use \r and later \n or \r\n.
      String[] result = new String[2];
      int index = lines.indexOf("\r\n");
      if(index != -1){
          result[0] = lines.substring(0, index + 2);
          result[1] = lines.substring(index + 2);
      }else if((index = lines.indexOf('\r')) != -1){
          result[0] = lines.substring(0, index + 1);
          result[1] = lines.substring(index + 1);
      }else if((index = lines.indexOf('\n')) != -1){
          result[0] = lines.substring(0, index + 1);
          result[1] = lines.substring(index + 1);   
      }else{   //no line seperators found
          result[0] = "";
          result[1] = lines;
      }
      return result;
  }

}
