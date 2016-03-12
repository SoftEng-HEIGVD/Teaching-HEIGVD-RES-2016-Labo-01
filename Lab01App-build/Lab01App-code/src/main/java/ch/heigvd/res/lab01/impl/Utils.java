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
    String[] n = new String[2];

    int macos = lines.indexOf("\r");
    int unix = lines.indexOf("\n");
    int win = lines.indexOf("\r\n");

    if(win >= 0){
      n[0] = lines.substring(0, win+2);
      n[1] = lines.substring(win+2, lines.length());
    } else if (macos >= 0){
      n[0] = lines.substring(0, macos+1);
      n[1] = lines.substring(macos+1, lines.length());
    } else if (unix >= 0){
      n[0] = lines.substring(0, unix+1);
      n[1] = lines.substring(unix+1, lines.length());
    } else {
      n[0] = "";
      n[1] = lines;
    }

    return n;
  }

}
