package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());
  private static final String[] END_LINES_CHAR = {"\r", "\n"};

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
    String[] output = {"", ""};
    int indexEndLine = -1;

    /* look for of a character of end line */
    for (int i = 0; indexEndLine == -1 && i < END_LINES_CHAR.length; i++)
      indexEndLine = lines.indexOf(END_LINES_CHAR[i]);

    /* if a character of end line is found */
    if (indexEndLine != -1) {
      /* if this character is not the last character */
      if (indexEndLine + 1 < lines.length()) {

        /* if there is two characters of end line (\r\n) */
        if (lines.charAt(indexEndLine + 1) == '\n') {
          output[0] = lines.substring(0, indexEndLine + 2);
          output[1] = lines.substring(indexEndLine + 2);

        } else { // if there is just one character of end line
          output[0] = lines.substring(0, indexEndLine + 1);
          output[1] = lines.substring(indexEndLine + 1);
        }

      } else { // if this character is the last, then the input string is one line
        output[0] = lines;
      }

    } else { // if there is no character of end line
      output[1] = lines;
    }

    return output;
  }

}
