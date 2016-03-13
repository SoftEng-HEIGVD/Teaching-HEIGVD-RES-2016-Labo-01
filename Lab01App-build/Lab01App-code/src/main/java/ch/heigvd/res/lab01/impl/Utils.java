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
        
        String[] linesArray = new String[2];
        int index = 0;
        boolean newLineOccurence = false;
        
        if(lines.contains("\r\n")){
            index=lines.indexOf("\r\n") + 1;
            newLineOccurence = true;
        }else if(lines.contains("\r")){
            index=lines.indexOf("\r");
            newLineOccurence = true;
        }else if(lines.contains("\n")){
            index= lines.indexOf("\n");
            newLineOccurence = true;
        }
        if(newLineOccurence){
            linesArray[0] = lines.substring(0, index + 1);
            linesArray[1] = lines.substring(index + 1);
        }else{
            linesArray[0] = "";
            linesArray[1] = lines;            
        }  
        return linesArray;
    }
}                
        /*
        
        for (int i = 0; i < lines.length(); i++) {
            char c = lines.charAt(i);
            
            if (c == '\n' || c == '\r') {

                if (c == '\r' && i != (lines.length())) {
                    if (lines.charAt(i + 1) == '\n') {
                        i++;
                        linesArray[0] = lines.substring(0, i + 1);
                        linesArray[1] = lines.substring(i + 1);
                        return linesArray;
                    }
                }
                linesArray[0] = lines.substring(0, i + 1);
                linesArray[1] = lines.substring(i + 1);
                return linesArray;
            }
        }
        linesArray[0] = "";
        linesArray[1] = lines;
        return linesArray;
    }   
}  */  //Process char

    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  


