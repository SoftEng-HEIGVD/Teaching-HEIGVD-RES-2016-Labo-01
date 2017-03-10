package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Olivier Liechti
 * @author Pierre-Benjamin Monaco
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

    //Using regex Pattern to look for delimiters
    Pattern pattern = Pattern.compile("\r\n|\r|\n");
    Matcher matcher = pattern.matcher(lines);

    //If one of the 3 delimiters is found
    if(matcher.find()){
      //Defining offset for substrings
      //Allows to control substring bounds for multichar delimiters.
      int offset = 1;
      if(lines.indexOf("\r\n") != -1)
      {
        ++offset;
      }
      //Returns an Array with parsed string
      return new String[]{lines.substring(0,matcher.start() + offset),lines.substring((matcher.start() + offset))};
    }
    else
    {
      //Returns an Array with an emply string and the whole string line
      return new String[]{"",lines};
    }
  }

}
