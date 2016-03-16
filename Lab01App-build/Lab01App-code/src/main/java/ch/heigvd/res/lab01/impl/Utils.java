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
      final String[] lineSeparator = {"\r", "\n", "\r\n"};
      String[] result = {"", ""};
      int size = 0;
      boolean proof = false;
     
      for(String sapare : lineSeparator){
          if(!lines.contains(lineSeparator[2])){
                if(lines.contains(sapare)){ //The text content on of the current lines separator
                    size = lines.indexOf(sapare) + sapare.length();
                    result[0] = lines.substring(0, size); // the first line
                    result[1] = lines.substring(size); // the reste of the text
                    proof = true;
                    break;
                }
          }else{
                size = lines.indexOf(lineSeparator[2]) + lineSeparator[2].length();
                result[0] = lines.substring(0, size); // the first line
                result[1] = lines.substring(size); // the reste of the text
                proof = true;
                break;
          }
      }
      if(!proof){
          result[1] = lines; 
      }
      return result;
  }

}
