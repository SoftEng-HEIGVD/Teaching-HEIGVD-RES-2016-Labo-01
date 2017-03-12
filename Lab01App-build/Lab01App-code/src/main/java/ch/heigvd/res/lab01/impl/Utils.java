package ch.heigvd.res.lab01.impl;

import java.lang.reflect.Array;
import java.util.LinkedList;
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
      String[] l = new String[2]; // Array to return
      l[0] = "";
      l[1] = "";
      String line1; // For the first parameter in the returned array
      String line2; // For the second parameter in the returned array
      for(int i = 0; i < lines.length(); ++i){
          // If we have "\r\n"
          if((i + 1) < lines.length() && lines.charAt(i) == '\r' && lines.charAt(i + 1) != '\0'){
              if(lines.charAt(i + 1) == '\n'){
                line1 = lines.substring(0, i + 2);
                line2 = lines.substring(i + 2);
                ++i;
                l[0] = line1;
                l[1] = line2;
                break;
              }else{
                line1 = lines.substring(0, i + 1);
                line2 = lines.substring(i + 1);
                l[0] = line1;
                l[1] = line2;
                break;
              }
              // If we have only '\n'
          }else if(lines.charAt(i) == '\n'){
                line1 = lines.substring(0, i + 1);
                line2 = lines.substring(i + 1);
                l[0] = line1;
                l[1] = line2;
                break;
                // If we have only '\n' or '\r' at the end of the string
          }else if(lines.charAt(i) == '\n' || lines.charAt(i) == '\r'){
                String line = lines.substring(0, i + 1);
                l[0] = line;
                l[1] = "";
          }else{
              // If there is only one line, it returns first empty and the second
              // parameter the line itself
                l[0] = "";
                l[1] = lines;
          }
      }
      
      return l;
  }

}
