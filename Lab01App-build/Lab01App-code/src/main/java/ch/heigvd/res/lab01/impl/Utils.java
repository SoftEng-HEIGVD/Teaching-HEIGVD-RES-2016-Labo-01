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
      String[] s = new String[2];
      String separator ="";
      
      // find first position separator and save symbol separator
      int pos = lines.indexOf("\r\n");
      if (pos == -1) {
         pos = lines.indexOf("\r");
         if (pos == -1) {
            pos = lines.indexOf("\n");
            if (pos!=-1){
               separator = "\n";
            }
         }else{
            separator = "\r";
         }
      }else{
         separator = "\r\n";
      }

      if (pos == -1) { // not separator, first line is empty, second is lines
         s[0] = "";
         s[1] = lines;
      } else {
         s[0] = lines.substring(0, pos+separator.length());
         s[1] = lines.substring(pos+separator.length());
      }
      return s;
   }

}
