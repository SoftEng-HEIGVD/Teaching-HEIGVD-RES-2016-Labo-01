package ch.heigvd.res.lab01.impl;

import java.util.ArrayList;
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
      ArrayList<String> ret = new ArrayList();
      int pos = 0;
      int tempPos;
      if(lines.contains("\r\n")){
          while((tempPos = lines.indexOf("\r\n", pos + 1)) != -1){
              ret.add(lines.substring(pos, tempPos));
              pos = tempPos;
          }
      }
      return (String[])ret.toArray();
  }

}
