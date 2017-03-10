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

      String[] lineTab = new String[2];
      
      /* Initialize an empty string */
      lineTab[0] = "";
      lineTab[1] = "";
      
      String[] separators = {"\r\n","\r", "\n"};
      int index = -1;
      
      //Check if the string given is containing one of the separators
      for (String separator : separators) {
         
         if (lines.contains(separator)) {
            
            index = lines.indexOf(separator);
            //Create the next line
            lineTab[0] = lines.substring(0,index + 1);
         }
      }
      
      //return the remaining text
      lineTab[1] = lines.substring(index + 1,lines.length());
      
      return lineTab;
   }

}
