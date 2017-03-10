package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Denise Gemesio
 */
public class Utils {

   private static final Logger LOG = Logger.getLogger(Utils.class.getName());

   /**
    * This method looks for the next new line separators (\r, \n, \r\n) to extract
    * the next line in the string passed in arguments.
    *
    * @param lines a string that may contain 0, 1 or more lines
    * @return an array with 2 elements; the first element is the next line with the
    * line separator, the second element is the remaining text. If the argument does
    * not contain any line separator, then the first element is an empty string.
    */
   public static String[] getNextLine(String lines) {
      
      // We create the array of length 2
      String[] e = new String[2];

      // If there is no return sign, we put an empty string in the first entry and the
      // line in the second entry and we return it
      if (!(lines.contains("\r") || lines.contains("\n") || lines.contains("\r\n"))) {
         e[0] = "";
         e[1] = lines;
         return e;
      }

      // We create a counter to know the position of the return character and a boolean
      // to know when we can stop searching
      int counter = 0;
      boolean foundChar = false;

      // If it contains a "\r\n", then the substring from the index in which the counter
      // found the return character will be increased by one to cover the '\n'. We will
      // then store the line until the '\n' in the first entry of the array and the rest
      // of the line in the second entry.
      // Testing the same for the '\r' and '\n' separatedly doesn't change much a part
      // from the fact that we will not increase the counter.
      if (lines.contains("\r\n")) {
         while (counter < lines.length() && !foundChar) {
            if (lines.charAt(counter) == '\r') {
               foundChar = true;
            }
            ++counter;
         }
         e[0] = lines.substring(0, counter + 1);
         e[1] = lines.substring(counter + 1);

      } else if (lines.contains("\r") || lines.contains("\n")) {
         while (counter < lines.length() && !foundChar) {
            if (lines.charAt(counter) == '\r' || lines.charAt(counter) == '\n') {
               foundChar = true;
            }
            ++counter;
         }
         e[0] = lines.substring(0, counter);
         e[1] = lines.substring(counter);

      }
      
      foundChar = false;
      return e;

   }
}
