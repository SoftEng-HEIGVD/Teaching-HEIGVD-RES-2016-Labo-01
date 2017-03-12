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
  public static String[] getNextLine(String lines) {
      //si la ligne ne contient aucun caractère de retour de ligne on retourne une igne vide
     if(!lines.contains("\r") && !lines.contains("\n"))
     {
        String[] array = {"", lines};
        return array;
     }
     String[] array = lines.split("\r\n|\r|\n", 2);
     //split supprime le caractère de fin de ligne on rajoute donc le caractère suivant la ligne
     array[0] += lines.charAt(array[0].length());
     
     
     //on rajoute aussi le deuxième si le caractère de fin de ligne est '\r' + '\n'
     if(array[0].length() < lines.length() && lines.charAt(array[0].length() - 1) == '\r' && lines.charAt(array[0].length()) == '\n')
     {
         array[0] += lines.charAt(array[0].length());
     }
     return array;
  }

}
