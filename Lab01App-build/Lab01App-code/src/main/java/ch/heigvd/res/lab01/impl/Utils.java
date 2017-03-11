package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Olivier Liechti
 * @author Luana Martelli
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

    String[] tokens = {"", ""};

    /* We use regex to find a pattern in the lines.
     * () means a group
     * For the first group, we are looking for ^(.*[\r\n]+)
     * which means from the beginning of the sentence, we want any non-return character (by default, line
     * separator are not counted ans ends up with one or many of either \r or \n
     * For the second group, we are looking for ((?s).*)$
     * which means that the group will contain anything (even the line separators) until the end ($)
     * of the sentence
     * If there is a match of the pattern in the sentence, then we split the sentence in the array
     * Otherwise, we juste write the sentence in tokens[1] as asked */
    Pattern p = Pattern.compile("^(.*[\r\n]+)((?s).*)$");
    Matcher m = p.matcher(lines);
    if (m.find()) {
      tokens[0] = m.group(1);
      tokens[1] = m.group(2);
    } else {
      tokens[1] = lines;
    }

  return tokens;
  }
}
