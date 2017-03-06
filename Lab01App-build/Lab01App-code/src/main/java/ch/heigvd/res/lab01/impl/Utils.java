package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    // Use regular expressions to go throught the string and search for one of the three line separators.
    // If the pattern matche, divides the string just after the end of the term found
    // Pattern : \r\n OR \r OR \n, Windows \r\n must be matched first
    Pattern pattern = Pattern.compile("(\r\n|\r|\n)");
    Matcher matcher = pattern.matcher(lines);
    if (matcher.find()) {
      return new String[] {
          lines.substring(0, matcher.end()),
          lines.substring(matcher.end())
      };
    }

    // There is no new line separtor.
    // Returns the array with the full lines in second position.
    return new String[] { "", lines };
  }

}
