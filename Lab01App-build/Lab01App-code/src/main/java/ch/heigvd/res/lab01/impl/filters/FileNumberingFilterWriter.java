package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int LINE_CPT = 1;
    private boolean FIRST_TIME = true;
    boolean WAIT_NEXT_CHAR = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        str = str.substring(off, off + len);

        String[] tmpTab = Utils.getNextLine(str);

        if (FIRST_TIME == true) {
            out.write(LINE_CPT + "\t");
            FIRST_TIME = false;
        }

        if (tmpTab[0].equalsIgnoreCase("")) {
            out.write(tmpTab[1]);
        }//  In this case str contain an EOL char
        else if (tmpTab[1].equalsIgnoreCase("")) {
            LINE_CPT++;
            out.write(tmpTab[0] + LINE_CPT + "\t");
        }// In this case str contain more than one string.
        else {
            LINE_CPT++;
            out.write(tmpTab[0] + LINE_CPT + "\t");
            this.write(tmpTab[1]);
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        String tmp = "";
        int i = 0;
        while (i < len) {
            tmp += cbuf[i + off];
            i++;
        }
        this.write(tmp);
    }

    @Override
    public void write(int c) throws IOException {

        char tmpChar = (char) c;
        
        // If the current char is \r we set the variable WAIT_NEXT_CHAR to true
        // in case if the next char is a \n.
        if(tmpChar == '\r'){
            WAIT_NEXT_CHAR = true;
            return;
        }
        
        // if the previous char was a \r
        if(WAIT_NEXT_CHAR){
            // and if the current char is a \n we have to deal with windows EOF.
            if(tmpChar == '\n'){
                LINE_CPT ++;
                out.write("\r\n" + LINE_CPT + "\t");
                WAIT_NEXT_CHAR = false;
            }// If it was a false alarm write the last \r and the current
             // char.
            else{
                LINE_CPT ++;
                out.write("\r" + LINE_CPT + "\t");
                WAIT_NEXT_CHAR = false; 
            }
        }
        else{
            this.write(Character.toString(tmpChar));
            WAIT_NEXT_CHAR = false;
        }
        
    }

}
