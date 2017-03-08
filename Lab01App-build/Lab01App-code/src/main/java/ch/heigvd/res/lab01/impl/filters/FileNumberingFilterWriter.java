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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int lineNumber = 0; // remember the line's number
    private boolean lastIsCarriageReturn = false; // rember if the last written char was a '\r' (MAC/DOS compatibility)

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = off; i < len + off; i++) {
            // write character by character
            write(str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(String.valueOf(cbuf, off, len));
    }

    @Override
    public void write(int c) throws IOException {
        // Number the first line or after a '\r' if it isn't followed by '\n' (DOS/MAC compatibility)
        if (lineNumber == 0 || (lastIsCarriageReturn && c != '\n')) {
            out.write(++lineNumber + "\t");
        }

        // Write char
        out.write(c);

        // Number the next line if there's a '\n'
        if (c == '\n') {
            out.write(++lineNumber + "\t");
        }

        // Remember if c is a '\r'
        lastIsCarriageReturn = (c == '\r');
    }
}
