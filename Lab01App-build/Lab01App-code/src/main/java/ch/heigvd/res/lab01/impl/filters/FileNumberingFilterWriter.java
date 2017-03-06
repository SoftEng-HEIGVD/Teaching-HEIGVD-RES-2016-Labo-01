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

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    private boolean firstLine = true;
    private int lineNumber;
    private boolean waitingForbackN = false;

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            this.write(str.charAt(off+i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            this.write(cbuf[off+i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        // if it's first line
        if (firstLine) {
            lineNumber++;
            out.write(String.valueOf(lineNumber)+"\t");
            firstLine = false;
        }
        // x = any char
        // if it's c is \n
        if ((char)c == '\n') {
            // if \r\n
            if (waitingForbackN) {
                out.write("\r");
            }
            out.write((char)c);
            lineNumber++;
            out.write(String.valueOf(lineNumber)+"\t");
            waitingForbackN = false;
        } else if ((char)c == '\r') {
            // if \r\r
            if (waitingForbackN) {
                out.write("\r"+(char)c);
                lineNumber++;
                out.write(String.valueOf(lineNumber) + "\t");
            }
            // if \r
            waitingForbackN = true;
        } else {
            // if \rx
            if (waitingForbackN) {
                out.write("\r");
                lineNumber++;
                out.write(String.valueOf(lineNumber) + "\t");
            }
            // if x
            out.write((char)c);
            waitingForbackN = false;
        }
    }

}
