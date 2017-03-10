package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;


import java.lang.String;

/**
 *
 * @author Olivier Liechti
            Modified by Tano Iannetta
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

      String[] ArrayLines = new String[2];

      int pos = lines.indexOf('\r', 0);
      if(pos != -1) // \r found
      {
          ArrayLines[0] = lines.substring(0, ++pos);

          // windows
          if(pos < lines.length() && lines.charAt(pos) == '\n')
          {
              ArrayLines[0] += '\n';
              pos++;
          }

      }
      else   // looking for only \n, unix
      {
          pos = lines.indexOf('\n', 0);
          if(pos != -1) // found
          {
              ArrayLines[0] = lines.substring(0, ++pos);
          }
          else // no separators
          {
             ArrayLines[0] = "";
             ArrayLines[1] = lines;
             return ArrayLines;
          }
      }

      ArrayLines[1] = lines.substring(pos); // rest of lines
      return ArrayLines;
  }

}
