package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 * Modified by Pascal Sekley
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
     
     String separatorFound = "";
     String[] ArrayOfLines = {"", ""};
     String[] ArrayOfSeparators = {"\n", "\r", "\r\n"};
     
     // Get the index of the separator in the ligne, that will help us to know where to stop
     // during extraction of lines
     int indexOfSeparator = -1;
     
     // Try to find any separator in the line passed in argument
     for (String ArrayOfSeparator : ArrayOfSeparators) {
        indexOfSeparator = lines.indexOf(ArrayOfSeparator);
        if (indexOfSeparator != -1) {
           separatorFound = ArrayOfSeparator;
           break;
        }
     }
     // No separator found in the line so we copy the ligne in the second position of the arry
     if(indexOfSeparator == -1){
           ArrayOfLines[1] = lines;
        }
     // We found one separator 
     else{
         ArrayOfLines[0] = lines.substring(0, indexOfSeparator + separatorFound.length());
         ArrayOfLines[1] = lines.substring(indexOfSeparator + separatorFound.length());
      }

     return ArrayOfLines;
  }

}
