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
      String[] separatedLines = new String[2];
      String[] separatorsList = {"\r\n", "\n", "\r"};// we must first of all look for \r\n, then \n or \r
      int pos;
      for (String s : separatorsList) {
         pos = lines.indexOf(s);
         if (pos != -1) {
            separatedLines[0] = lines.substring(0, pos + s.length());
            separatedLines[1] = lines.substring(pos + s.length());
            return separatedLines;
         }
      }
      
      //if we do not find any line separator
      separatedLines[0] = "";
      separatedLines[1] = lines;
      return separatedLines;
   }

}
