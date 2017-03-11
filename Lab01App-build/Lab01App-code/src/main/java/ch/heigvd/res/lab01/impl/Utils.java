package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 * @author Olivier Liechti
 * @ModifiedBy Ali Miladi
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
        String[] tokens = new String[2];
        int found; //Store cut index here.
        //Linux users.
        if ((found = lines.indexOf("\n")) != -1) {
            tokens[0] = lines.substring(0, found + 1);
            tokens[1] = lines.substring(found + 1);
            return tokens;
        }
        //MacOS users.
        else if ((found = lines.indexOf("\r")) != -1){
            tokens[0] = lines.substring(0, found + 1);
            tokens[1] = lines.substring(found + 1);
            return tokens;
        }
        //Windows users.
        else if ((found = lines.indexOf("\r\n")) != -1){
            tokens[0] = lines.substring(0, found + 2);
            tokens[1] = lines.substring(found + 2);
            return tokens;
        }
        //No linefeed characters.
        else{
            tokens[0] = "";
            tokens[1] = lines;
            return tokens;
        }
    }
}
