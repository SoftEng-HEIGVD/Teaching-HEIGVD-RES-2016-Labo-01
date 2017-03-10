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
    * @return an array with 2 elements; the first element is the next line with the
    * line separator, the second element is the remaining text. If the argument does
    * not contain any line separator, then the first element is an empty string.
    */
   public static String[] getNextLine(String lines) {

      String[] e = new String[2];

      if (!(lines.contains("\r") || lines.contains("\n") || lines.contains("\r\n"))) {
         e[0] = "";
         e[1] = lines;
         return e;
      }

      int counter = 0;

      if (lines.contains("\r\n")) {
         while (counter < lines.length()) {
            if (lines.charAt(counter) == '\r') break;
            ++counter;
         }
         
         e[0] = lines.substring(0, counter + 2);
         e[1] = lines.substring(counter + 2);

      } else if (lines.contains("\r")) {
         while (counter < lines.length()) {
            if (lines.charAt(counter) == '\r') break;
            ++counter;
         }
         
         e[0] = lines.substring(0, counter + 1);
         e[1] = lines.substring(counter + 1);

      } else {
         while (counter < lines.length()) {
            if (lines.charAt(counter) == '\n') break;
            ++counter;
         }
         
         e[0] = lines.substring(0, counter + 1);
               e[1] = lines.substring(counter + 1);
      }

      return e;
      
   }
}
