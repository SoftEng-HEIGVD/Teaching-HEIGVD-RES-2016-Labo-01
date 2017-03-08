package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Adrien Marco
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

    String lineFile[] = new String[2];

    //types of seperators
    String sep1 = "\r";
    String sep2 = "\n";
    String sep3 = "\r\n";

    //stocks the place of the separator in the string
    int index;

    //if we find the separator \r\n
    if((index = lines.indexOf(sep3))!= -1){
      lineFile[0] = lines.substring(0, index + sep3.length());
      lineFile[1] = lines.substring(index + sep3.length(), lines.length());
    }
    //or if we find the separator \n
    else if((index = lines.indexOf(sep2))!= -1){
      lineFile[0] = lines.substring(0, index + sep2.length());
      lineFile[1] = lines.substring(index + sep2.length(), lines.length());
    }
    //or if we find the separator \r
    else if((index = lines.indexOf(sep1))!= -1){
      lineFile[0] = lines.substring(0, index + sep1.length());
      lineFile[1] = lines.substring(index + sep1.length(), lines.length());
    }
    //or finally if there is no sepatator
    else{
      lineFile[0] = "";
      lineFile[1] = lines;
    }
    return lineFile;
  }

}
