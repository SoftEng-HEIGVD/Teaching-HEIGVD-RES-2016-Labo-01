
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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author David Truan
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int previousChar;
    private boolean isFirstChar = true;
    private int lineCount = 0;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(),off,len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; ++i) {
            write(cbuf[i]);
        }

    }

    @Override
    public void write(int c) throws IOException {
        // check if it is the first char. If so we write the header
        if (isFirstChar) {
            isFirstChar = false;
            super.out.write(++lineCount + "\t");
        }
        
        // here we check if the char is a '\n', if so we copy it and write the
        // header
        // if the previous char was a '\r' (if only '\r' is used as nextline
        // char) we write the header and then write the char
        // else we only write the char
        if (c == '\n') {
            out.write(c);
            super.out.write(++lineCount + "\t");
        } else if (previousChar == '\r') {
            super.out.write(++lineCount + "\t");
            out.write(c);

        } else {
            out.write(c);
        }
        previousChar = c;
    }
}
