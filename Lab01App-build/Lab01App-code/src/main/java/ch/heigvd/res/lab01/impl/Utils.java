package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * Utility class providing useful method
 * @author Olivier Liechti
 * @modified Colin Lavanchy
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

    if(lines.isEmpty())
        return new String[]{};

    else{

        //Trying to determine which separator is used
        boolean isN = lines.contains("\n");
        boolean isRN = lines.contains("\r\n");
        boolean isR = lines.contains("\r");

        //Getting the only non null value
        String separator = isRN?"\r\n":isN?"\n":"\r";

        if(!isN&&!isRN&&!isR)
            return new String[]{"",lines};

        return new String[]{lines.split(separator)[0]+separator,lines.substring(lines.indexOf(separator)+(isRN?2:1))};

    }

  }

}
