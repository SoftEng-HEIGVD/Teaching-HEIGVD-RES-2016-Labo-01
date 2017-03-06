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
  
  //This is the set of elements that will determine that a string of characters contains more than a line 
  private static String[] nextlineSeparators = {"\r\n", "\r","\n"};
   public static String[] getNextLine(String lines) { 
      
      String[] result = new String[2];

      int indexOf = -1; // The default value -1 indicates that the separator was not found  
      for (String separator : nextlineSeparators) {
         indexOf = lines.indexOf(separator);
         if (indexOf != -1) {// a new line separator has been found 
            result[0] = lines.substring(0, indexOf + separator.length()); //The next line is inserted in the array at index 0
            
            result[1] = lines.substring(indexOf + separator.length());//The rest of the input string if any is inserted in the 
                                                                       //array at index 1
            return result;
         }
      }
     
      //No separator has been found 
      return result = new String[]{"",lines};
   }

}
