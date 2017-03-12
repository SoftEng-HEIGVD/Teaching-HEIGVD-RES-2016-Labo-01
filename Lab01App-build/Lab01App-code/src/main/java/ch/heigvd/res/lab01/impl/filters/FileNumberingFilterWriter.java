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

    private int compteurLigne = 0;
    private boolean rDelim = false;

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(), off, len); // Transform String into Array of char
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
       /* Write char by char using write(int c) methode */
        for (int i = 0; i < len; i++) {
            write(cbuf[off + i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        /* Case of 1st line */
        if (compteurLigne == 0) {
            compteurLigne++;
            out.write(compteurLigne + "\t");
        }
        /* Case of last char was \r (bool) and current chat is \n */
        if (rDelim && c != '\n') {
            compteurLigne++;
            out.write(compteurLigne + "\t");

        }

        rDelim = false; //Current char isn't \r anymore

        /* Case of current char is \r */
        if (c == '\r') {
            rDelim = true; //Use boolean to remember
            out.write(c);
        } else {
            out.write(c);
            if (c == '\n') { // Case current char is \n
                compteurLigne++;
                out.write(compteurLigne + "\t");
            }
        }
    }
}
