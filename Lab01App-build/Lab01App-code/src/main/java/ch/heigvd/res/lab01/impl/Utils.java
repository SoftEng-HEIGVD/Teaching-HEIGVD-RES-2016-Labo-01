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
    String[] sep = new String[2];
    int indexSep;

    for(indexSep = 0; indexSep < lines.length(); indexSep++) {
      char c = lines.charAt(indexSep);
      if(c == '\r' || c == '\n') {
        break;
      }
    }

    if(indexSep + 1 < lines.length() && lines.charAt(indexSep) == '\r' && lines.charAt(indexSep + 1) == '\n') {
      indexSep++;
    }

    if(indexSep < lines.length()) {
      indexSep++;
      sep[0] = lines.substring(0, indexSep);
      sep[1] = lines.substring(indexSep);
    } else {
      sep[0] = "";
      sep[1] = lines.substring(0);
    }

    return sep;
  }

}
