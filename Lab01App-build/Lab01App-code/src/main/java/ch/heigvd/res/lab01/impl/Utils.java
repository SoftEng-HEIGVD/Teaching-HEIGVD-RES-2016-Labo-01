package ch.heigvd.res.lab01.impl;

import java.util.Vector;
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
    Vector<String> tabString = new Vector<>();
    int lastIndexOfNL = 0;


    if(lines.contains("\r\n"))
      for(int i=0;i<lines.length();i++){
        if(lines.charAt(i) == '\r'){
          tabString.add(lines.substring(lastIndexOfNL,i+1)+"\n");
          lastIndexOfNL = i+2;
          i++;
        }
      }
    else if(lines.contains("\r"))
      for(int i=0;i<lines.length();i++){
        if(lines.charAt(i) == '\r'){
          tabString.add(lines.substring(lastIndexOfNL,i)+"\r");
          lastIndexOfNL = i+1;

        }
      }
    else if(lines.contains("\n"))
      for(int i=0;i<lines.length();i++){
        if(lines.charAt(i) == '\n'){
          tabString.add(lines.substring(lastIndexOfNL,i)+"\n");
          lastIndexOfNL = i+1;

        }
      }
    else{
      tabString.add("");
      tabString.add(lines);
      return tabString.toArray(new String[0]);
    }

    if(tabString.size() == 1)
      tabString.add("");

    return tabString.toArray(new String[0]);
  }

}
