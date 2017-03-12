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
    int pos = -1;
    for(int i = 0; i< lines.length(); ++i){
      char c = lines.charAt(i);
      if(c == '\n' || c == '\r'){ //linux and ios
        pos = i;
        if(i+1<lines.length() && lines.charAt(i+1) == '\n'){
          pos = i+1;
        }
        break;
      }
    }
    //now that we have the position of the separator, we return an array
    String[] result = new String[2];
    if(pos == -1) {
      result[0] = "";
      result[1] = lines;

    }else{
      result[0] = lines.substring(0,pos+1);
      result[1] = lines.substring(pos+1);
    }
    return result;
  }

}
