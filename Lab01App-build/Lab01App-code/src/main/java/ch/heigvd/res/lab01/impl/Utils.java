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
    
    String tmp[] = new String[2];  
    int i = -1;
    int iR = 0;
    int iN = 0;
    int index = -1;
    
    if(lines.contains("\r\n")){
        index = lines.indexOf("\r\n");
    }
    
    if(lines.contains("\r") || lines.contains("\n")){
        iR = lines.indexOf("\r");
        iN = lines.indexOf("\n");

        // Check if "\r" occur, or if "\n" occur or if both occur, if so check 
        // wich one occur first.
        if(iR == -1){
            i = iN;
        }
        else if(iN == -1){
            i = iR;
        }
        else{
            if( iR > iN)
                i = iN;
            else
                i = iR;
        } 
    }
    
    // If both "\r""\n" and "\r\n" occur in the string we check wich one occur first.
    if(i != -1 && index != -1){
        // Check if "\r" and "\n" occur before "\r\n".  
        if(i < index){
            tmp[0] = lines.substring(0, i + 1);
            tmp[1] = lines.substring(i + 1);
        }
        else
        {
            tmp[0] = lines.substring(0, index + 2);
            tmp[1] = lines.substring(index + 2);
        }
    }// If the string contain only "\r\n".
    else if(index != -1){
        tmp[0] = lines.substring(0, index + 2);
        tmp[1] = lines.substring(index + 2);
    }// If the string contain only "\r" or "\n".
    else if(i != -1){
        tmp[0] = lines.substring(0, i + 1);
         tmp[1] = lines.substring(i + 1);
    }// If the string containt no EOL.
    else{
        tmp[0] = "";
        tmp[1] = lines;  
    }
    
    return tmp;
  }

}
