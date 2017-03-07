package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 */
public class Utils
{

   private static final Logger LOG = Logger.getLogger(Utils.class.getName());

   /**
    * This method looks for the next new line separators (\r, \n, \r\n) to extract
    * the next line in the string passed in arguments.
    *
    * @param lines a string that may contain 0, 1 or more lines
    * @return an array with 2 elements;
    *
    * the first element is the next line with the line separator
    * the second element is the remaining text.
    *
    * If the argument does not
    * contain any line separator, then the first element is an empty string.
    */
   public static String[] getNextLine(String lines)
   {
      // The string array where to store the required items
      String[] result = new String[2];

      // If we don't have any new line char in the string
      if(lines.indexOf('\n') == -1 && lines.indexOf('\r') == -1)
      {
         result[0] = new String("");
         result[1] = lines;
      }

      else if(lines.contains("\n"))
      {
         result[0] = lines.substring(0, lines.indexOf('\n') + 1);
         result[1] = lines.substring(lines.indexOf('\n') + 1);
      }
      else
      {
         result[0] = lines.substring(0, lines.indexOf('\r') + 1);
         result[1] = lines.substring(lines.indexOf('\r') + 1);
      }

      return result;
   }

   private static int getCharPos(char c, String str)
   {
      for (int i = 0; i < str.length(); i++)
      {
         if(c == str.charAt(i))
            return i;
      }

      return -1;
   }

}
