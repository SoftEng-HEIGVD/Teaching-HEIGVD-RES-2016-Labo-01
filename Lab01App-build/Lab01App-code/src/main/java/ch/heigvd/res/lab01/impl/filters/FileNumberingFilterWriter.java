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
 * @author Mathias Gilson
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineNumber = 0;
    // indicate that this is the first char
    private boolean first = true;
    //carriage return detected
    private boolean CR = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        // Change the string into a charArray and send to corresponding method
        write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // call the write(int c) method for each char in the charArray
        for(int i = off; i < len + off; i++)
            write(cbuf[i]);
    }

    @Override
    public void write(int c) throws IOException {
        char charC = (char)c;

        // add a number if is first line or as a carriage return flag but is not a new line
        if((first || CR) && charC != '\n'){
            out.write(++lineNumber + "\t");
            first = false;
            CR = false;
        }

        out.write(charC);

        // Unix and windows new line, carriage return flag
        // is set to false because it's handled by default
        if(charC == '\n'){
            CR = false;
            out.write(++lineNumber + "\t");
        }
        else if(charC == '\r'){
            CR = true;
        }

    }

}
