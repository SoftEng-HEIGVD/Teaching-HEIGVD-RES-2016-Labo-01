package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

   private static final Logger LOG = Logger.getLogger(Utils.class.getName());
   
   private static final String[] DELIMITERS = {"\r\n", "\r", "\n"};

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
      int index = -1; // Index at which we would find the delimiters
      
      
      // Go through delimiters and continue while we haven't find one
      for (int i = 0; i < DELIMITERS.length; ++i) {
         if (index == -1) {
            index = lines.indexOf(DELIMITERS[i]);
            if (index != -1)
               index += DELIMITERS[i].length() - 1; // place the index at the end
         } else {
            break;
         }
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
