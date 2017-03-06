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
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int line_number = 0;
    private char Previous_character = '\0';

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        for (int i = off; i < off + len; ++i) {
            this.write(str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.write(new String(cbuf), off, len);

    }

    @Override
    public void write(int c) throws IOException {

        if (line_number == 0) {
            line_number = 1;
            super.write('1');
            super.write('\t');
        }

        if (c == '\n') {
            if (Previous_character != '\r') {
                line_number++;
                super.write('\n');
                String str = Integer.toString(line_number);
                super.write(str.charAt(0));

                super.write('\t');
            } else {
                line_number++;
                super.write('\n');
                String str = Integer.toString(line_number);
                super.write(str.charAt(0));
                super.write('\t');
            }
        } else {
            if (Previous_character == '\r') {
                line_number++;
                String str = Integer.toString(line_number);

                super.write(str.charAt(0));
                super.write('\t');
            }
            super.write(c);
        }
        Previous_character = (char) c;
    }

}
