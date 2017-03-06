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
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int lineNo = 1;
    private boolean cr = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    /*
     *  Call write(int) for every char in the string, starting at offset
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        for(int i = 0; i < len; i++) {
            write(str.charAt(off + i));
        }
    }

    /*
     *  Call write(int) for every char in the char array, starting at offset
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = 0; i < len; i++) {
            write(cbuf[off + i]);
        }
    }

    /*
     *  Writes chars one by one into the Stream and prepends the line number
     */
    @Override
    public void write(int c) throws IOException {
        // if it is line 1 then print number and tab
        if(lineNo == 1) {
            out.write(lineNo + "\t");
            lineNo++;
        }

        // is this char a cr?
        if(c == '\r')
            cr = true;
        // if char is a nl
        else if(c == '\n') {
            // if cr was just before then print it
            if (cr) {
                out.write("\r");
                cr = false;
            }
            // write char and line number + tab
            out.write(c);
            out.write(lineNo + "\t");
            lineNo++;
        }
        // it wasn't a nl
        else {
            // a cr was present before so print it with line number + tab
            if (cr) {
                out.write("\r");
                out.write(lineNo + "\t");
                lineNo++;
                cr = false;
            }
            // finally write current char
            out.write(c);
        }
    }

}
