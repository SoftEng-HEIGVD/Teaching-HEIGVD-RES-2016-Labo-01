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
        String [] ret = new String[2]; 
        
        int find = -1;
        for(int i = 0; i < lines.length(); i++){
            if(lines.charAt(i) == '\r'){
                find = i + 1;
                if(i + 1 < lines.length() && lines.charAt(i + 1) == '\n')
                    find++;
                break;
            }
            
            if(lines.charAt(i) == '\n'){
                find = i + 1;
                break;
            }
        }
        
        if(find > 0){
            ret[0] = lines.substring(0, find);
            ret[1] = lines.substring(find);
        } else {
            ret[0] = "";
            ret[1] = lines;
        }
        
        return ret;
    }
    
}
