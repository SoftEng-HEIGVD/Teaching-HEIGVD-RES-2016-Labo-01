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
     String[] line = new String[2];

     // Windows
     int i = lines.indexOf("\r\n");
     if(i != -1) {
        line[0] = lines.substring(0, i + 2);
        line[1] = lines.substring(i + 2);
        return line;
     }

     // Mac OS
     i = lines.indexOf("\r");
     if(i != -1) {
        line[0] = lines.substring(0, i + 1);
        line[1] = lines.substring(i + 1);
        return line;
     }

     // Unix
     i = lines.indexOf("\n");
     if(i != -1) {
        line[0] = lines.substring(0, i + 1);
        line[1] = lines.substring(i + 1);
        return line;
     }

     line[0] = "";
     line[1] = lines;
     return line;
  }

}
