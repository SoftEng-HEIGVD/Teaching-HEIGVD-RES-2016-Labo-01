package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Daniel Palumbo
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

    String result[] = {"", lines};

    // position of the second line
    int posOfSecondLine = -1;

    // loop threw the line characters
    for(int i = 0; i < lines.length(); i++){
      // check if end of a line
      if(lines.charAt(i) == '\r'){
        posOfSecondLine = i + 1;
        // end of line can be \r\n
        if(i + 1 < lines.length() && lines.charAt(i + 1) == '\n'){
          posOfSecondLine++;
        }
        break;
      }
      // end of line can be only \n
      else if(lines.charAt(i) == '\n'){
        posOfSecondLine = i + 1;
        break;
      }
    }

    if(posOfSecondLine != -1){
      result[0] = lines.substring(0, posOfSecondLine);
      result[1] = lines.substring(posOfSecondLine);
    }

    return result;
  }
}
