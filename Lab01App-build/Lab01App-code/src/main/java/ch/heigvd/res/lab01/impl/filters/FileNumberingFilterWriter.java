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

    @Override
    public void write(String str, int off, int len) throws IOException {
        for(int i = 0; i < len; i++) {
            write(str.charAt(off + i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = 0; i < len; i++) {
            write(cbuf[off + i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        if(lineNo == 1) {
            out.write(lineNo + "\t");
            lineNo++;
        }

        if(c == '\r')
            cr = true;
        else if(c == '\n') {
            if (cr) {
                out.write("\r");
                cr = false;
            }
            out.write(c);
            out.write(lineNo + "\t");
            lineNo++;
        }
        else {
            if (cr) {
                out.write("\r");
                out.write(lineNo + "\t");
                lineNo++;
                cr = false;
            }
            out.write(c);
        }
    }

}
