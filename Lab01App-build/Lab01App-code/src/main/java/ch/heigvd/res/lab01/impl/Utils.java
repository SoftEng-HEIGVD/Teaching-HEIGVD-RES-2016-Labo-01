package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

   private static final Logger LOG = Logger.getLogger(Utils.class.getName());

   /**
    * This method looks for the next new line separators (\r, \n, \r\n) to
    * extract the next line in the string passed in arguments.
    *
    * @param lines a string that may contain 0, 1 or more lines
    * @return an array with 2 elements; the first element is the next line with
    * the line separator, the second element is the remaining text. If the
    * argument does not contain any line separator, then the first element is an
    * empty string.
    */
   public static String[] getNextLine(String lines) {
      String[] output = {"", lines}; // Output array 
      int index = 0; // Index at which we would find the delimiters
      
      // Search for the first delimiter
      index = lines.indexOf("\r\n");
      
      if (index == -1) { // If not found, search for the other onw
         index = lines.indexOf('\r');
      } else { // If found, align index on last char of the delimiter
         index += 1;
      }
      if (index == -1) { // If still not found, search for the last
         index = lines.indexOf('\n');
      }
      if (index == -1) { // If not match found, return the default output
         return output;
      } 
      
      // Else, return the two lines
      output[0] = lines.substring(0, index + 1);
      output[1] = lines.substring(index + 1);
      
      return output;
      
   }

}
