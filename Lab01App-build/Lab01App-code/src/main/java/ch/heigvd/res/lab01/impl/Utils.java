package ch.heigvd.res.lab01.impl;

import java.util.ArrayList;
import java.util.List;
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

    String[] array = new String[2];
    String delimiter;

    //Try to find which delimiter is into the line
    if(lines.contains("\n")){
      delimiter = "\n";
    }
    else if(lines.contains("\r")){
      delimiter = "\r";
    }
    //If there is no delimiter
    else {
      array[0] = "";
      array[1] = lines;
      return array;
    }
    //Cut the string with the delimiter
    int idx = lines.indexOf(delimiter) + 1;
    array[0] = lines.substring(0, idx);

    //Case where the line contain other lines
    if(lines.length() > idx){
      array[1] = lines.substring(idx);
    }
    //Case where there is no other line
    else{
      array[1] = "";
    }
    return array;
  }

}
