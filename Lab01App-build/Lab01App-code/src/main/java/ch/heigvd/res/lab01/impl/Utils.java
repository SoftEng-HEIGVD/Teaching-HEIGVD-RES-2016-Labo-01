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
    int firstCarriage = lines.indexOf("\r");
    int firstNewLine  = lines.indexOf("\n");
    int secondLine = 0;

    if (firstCarriage != -1 && firstNewLine != -1) {
      // Windobe or linux EOF
      if (firstNewLine == firstCarriage + 1 || firstNewLine < firstCarriage) {
        secondLine = firstNewLine + 1;
      } else { // Mac OS EOL
        secondLine = firstCarriage + 1;
      }
    } else if (firstNewLine != -1) {
      secondLine = firstNewLine + 1;
    } else if (firstCarriage != -1) {
      secondLine = firstCarriage + 1;
    }

    return new String[] {
        lines.substring(0, secondLine),
        lines.substring(secondLine, lines.length())
    };
  }
}
