package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * @author Damien Carnal
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
      String[] result = new String[2];

      // si on a pas de nouvelle ligne on retourne la ligne actuel et un string vide 
      if (lines.indexOf('\n') == -1 && lines.indexOf('\r') == -1){
         result[0] = new String("");
         result[1] = lines;
      }
      //si il y a un caractère de saut de ligne on parse
      else if (lines.contains("\n")){
         result[0] = lines.substring(0, lines.indexOf('\n') + 1);
         result[1] = lines.substring(lines.indexOf('\n') + 1);
      }else{
         result[0] = lines.substring(0, lines.indexOf('\r') + 1);
         result[1] = lines.substring(lines.indexOf('\r') + 1);
      }

      return result;
  }

}
