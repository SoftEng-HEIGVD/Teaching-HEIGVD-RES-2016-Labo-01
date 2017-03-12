package ch.heigvd.res.lab01.impl.filters;

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
 * @author Aurelie Levy
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int cpt = 1;
    private boolean isFirstChar = true;
    private boolean hasR = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    /**
     * Allow to write a string with a filter (call write(char[], int off, int len)
     * @param str string to use
     * @param off offset
     * @param len length from the offset to use (for the string str)
     * @throws IOException 
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        
        char[] chars = str.toCharArray();//transform str in array
        this.write(chars, off, len);

        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    /**
     * Allow to write an array of char with a filter (call write(int c))
     * @param cbuf array of char
     * @param off offset 
     * @param len length from the offset to use (for the string str)
     * @throws IOException 
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        for (int i = off; i < off + len; i++) {
            this.write((int) cbuf[i]);//take char by char
        }
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    /**
     * Method used by the other write. Apply the filter (here: numbering the lines)
     * @param c character 
     * @throws IOException 
     */
    @Override
    public void write(int c) throws IOException {

        if (isFirstChar) {//if it's the first char, we have to number too
            outWrite();
            isFirstChar = false;
        }

        if (c == '\r') {//check if there is a \r because two case: MacOs or Windows
            hasR = true;
        } else if (c == '\n') {//if there is a \n: MacOs or Windows
            if (hasR) {//Windows
                out.write('\r');
                hasR = false;
            }
            out.write(c);
            outWrite();
        } else if (hasR) {//Unix
            out.write('\r');
            outWrite();
            out.write(c);
            hasR = false;
        } else {//not a separator
            out.write(c);
        }
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
        /**
         * write in out and increment the attribut cpt
         * @throws IOException 
         */
        private void outWrite() throws IOException{
        out.write(String.valueOf(cpt) + "\t");
        cpt++;
    }

}
