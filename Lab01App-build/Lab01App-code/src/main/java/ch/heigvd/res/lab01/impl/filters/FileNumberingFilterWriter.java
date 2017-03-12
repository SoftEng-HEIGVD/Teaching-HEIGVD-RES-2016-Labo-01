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

    private int nbLine;
    private char char_memory;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        nbLine = 0;
        char_memory = '\0';
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; ++i) {
            write((int) cbuf[off + i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        if (nbLine == 0) { //beginning of the file
            out.write(++nbLine + "\t");
        }
        //if last char was a \r but not a windows new line
        if (char_memory == '\r' && (char) c != '\n') {
            out.write(++nbLine + "\t");
        }
        out.write(c);

        //new line for linux and also for windows
        if ((char) c == '\n') { //Windows and linux
            out.write(++nbLine + "\t");

        }

        char_memory = (char) c;
    }


}
