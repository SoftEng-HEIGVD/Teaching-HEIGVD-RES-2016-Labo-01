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
    String[] result = new String[2];
    
    if(lines.contains("\n") || lines.contains("\r")){
        //regex "?<=" permet d'inclure le délimiteur avant la séparation
        //"\R correspond à tous les retours (\r,\n,\r\n)
        String[] splited = lines.split("(?<=\\R)");
        result[0] = splited[0];
        result[1] = lines.substring(splited[0].length());
        
        return result;
        
    }
    //Dans le cas où il n'y aurait pas de saut de ligne
    else{
        result[0] = "";
        result[1] = lines;
        return result;
    }
  }

}
