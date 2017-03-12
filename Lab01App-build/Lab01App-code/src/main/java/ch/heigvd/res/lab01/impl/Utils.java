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
  public static String[] getNextLine(String lines)
  {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    int idxNextNewLine = lines.indexOf("\n");
    int idxNextCarr  = lines.indexOf("\r");

    int idxNextLine = 0;

    if (idxNextCarr != -1 && idxNextNewLine != -1)
    {

      if (idxNextNewLine < idxNextCarr || idxNextNewLine == (idxNextCarr + 1)) // for windows and Linux
      {
        idxNextLine = idxNextNewLine + 1;
      }
      else // For MacOs
      {
        idxNextLine = idxNextCarr + 1;
      }
    }
    else if (idxNextNewLine != -1)
    {
      idxNextLine = idxNextNewLine + 1;
    }
    else if (idxNextCarr != -1)
    {
      idxNextLine = idxNextCarr + 1;
    }
    String firstElem = lines.substring(0, idxNextLine);
    String secondElem = lines.substring(idxNextLine, lines.length());
    return new String[] { firstElem, secondElem };
  }

}
