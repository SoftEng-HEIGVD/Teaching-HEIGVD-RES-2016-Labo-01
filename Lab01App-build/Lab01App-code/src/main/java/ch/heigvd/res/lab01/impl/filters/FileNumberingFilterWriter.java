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
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineCounter;
    private boolean workOnMac;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            this.write(str.charAt(i));
        }

    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            this.write(cbuf[i]);
        }

    }

    @Override
    public void write(int c) throws IOException {

        if (lineCounter == 0) {
            lineCounter++;
            out.write(String.valueOf(lineCounter));
            super.write('\t');

        }

        if (c == '\n') {
            super.write(c);
            lineCounter++;
            out.write(String.valueOf(lineCounter));
            super.write('\t');
            if (workOnMac) {
                workOnMac = false;
            }

        } else if (workOnMac) {
            lineCounter++;
            out.write(String.valueOf(lineCounter));
            super.write('\t');
            super.write(c);
            workOnMac = false;

        } else {
            super.write(c);
        }

        if (c == '\r') {
            workOnMac = true;
        }
    }
}
