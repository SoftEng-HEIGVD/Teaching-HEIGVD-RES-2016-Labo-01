package ch.heigvd.res.lab01.impl;

import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

    private static final Logger LOG = Logger.getLogger(Utils.class.getName());

    public static String[] getNextLine(String lines) {
        String[] line_separators = {"\n", "\r", "\r\n"}; //array of separators
        String[] result = new String[2];                 //array of return result
        int size = line_separators.length;
        for (int i = 0; i < size; ++i) {
            String separator = line_separators[i];
            int j = lines.indexOf(separator);  // search index of separator in  lines
            if (j != -1) {
                result[0] = lines.substring(0, j + separator.length()); // because separator don't have the same lenght
                result[1] = lines.substring(j + separator.length());
                return result;

            }
        }
        // we have not found no separator
        result[0] = "";
        result[1] = lines;
        return result;
    }
}
