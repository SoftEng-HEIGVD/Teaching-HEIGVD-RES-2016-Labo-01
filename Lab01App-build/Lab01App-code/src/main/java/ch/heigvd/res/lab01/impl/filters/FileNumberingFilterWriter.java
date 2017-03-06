package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer. When
 * filter encounters a line separator, it sends it to the decorated writer. It then
 * sends the line number and a tab character, before resuming the write process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private long nextLine = 1;
    private boolean wasReturn = false;
    private boolean wasEndOfLine = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(str.charAt(off + i));
        }

    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            write(cbuf[off + i]);
        }
    }

    @Override
    public void write(int c) throws IOException {

        // If nothing was written before
        if (nextLine == 1) {
            out.write(nextLine + "\t");
            nextLine++;
        }

        if (c == '\r') {
            wasReturn = true;
            out.write(c);

        } else if (c == '\n') {
            if (wasReturn) {
                wasReturn = false;
            }
            out.write(c);
            out.write(nextLine + "\t");
            nextLine++;
            wasEndOfLine = true;

        } else if (wasEndOfLine || wasReturn) {
            wasEndOfLine = false;
            if (wasReturn) {
                wasReturn = false;
                out.write(nextLine + "\t");
                nextLine++;
            }
            out.write(c);

        } else {
            out.write(c);
        }

    }

}
