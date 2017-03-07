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
    String[] out = new String[2];

    //presets the output
    out[0] = "";
    out[1] = lines;

    for (int i = 0; i < lines.length(); i++ ) { //end of the line ?
      if (lines.charAt(i) == '\n' || lines.charAt(i) == '\r') { //escape char
        if (i + 1 < lines.length() && lines.charAt(i+1) == '\n') { //windows
          i++;
        }

        out[0] = lines.substring(0,++i); //upperbound excluded
        out[1] = lines.substring(i, lines.length());
        return out;
      }

    }
    return out;
  }
}
