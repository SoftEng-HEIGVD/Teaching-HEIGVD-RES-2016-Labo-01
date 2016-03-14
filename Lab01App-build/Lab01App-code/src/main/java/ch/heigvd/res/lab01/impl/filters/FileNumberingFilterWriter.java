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
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    private int lineNo = 1;

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int n = 0; n < len; n++) {
            write(str.charAt(off + n));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int n = 0; n < len; n++) {
            write(cbuf[off + n]);
        }
    }

    private boolean hasCr = false;

    @Override
    public void write(int c) throws IOException {
        if (lineNo == 1) {
            writeLineNo();
        }

        char car = (char)c;
        if (car == '\r') {
            out.write('\r');
            hasCr = true;
        } else if (car == '\n') {
            if (hasCr) {
                hasCr = false;
            }
            out.write('\n');
            writeLineNo();
        } else {
            if (hasCr) {
                writeLineNo();
                hasCr = false;
            }
            out.write(car);
        }
    }

    private void writeLineNo() throws IOException {
        out.write(lineNo + "\t");
        lineNo++;
    }

    @Override
    public void flush() throws IOException {
        if (hasCr) {
            writeLineNo();
        }
        super.flush();
    }
}
