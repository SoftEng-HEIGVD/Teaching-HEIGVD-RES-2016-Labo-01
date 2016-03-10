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
    private int nbrLine = 1;

    public FileNumberingFilterWriter(Writer out) {

        super(out);

        try {
            super.write(nbrLine + "\t");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = off; i < len+off; i++) {
            if (str.charAt(i) == '\n') {
                super.write('\n');
                super.write(++nbrLine + "\t");
            } else if (str.charAt(i) == '\r') {
                super.write('\r');

                if (str.charAt(i + 1) == '\n') {
                    i++;
                    super.write('\n');
                }

                super.write(++nbrLine + "\t");

            } else {
                super.write(str.charAt(i));
            }
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        String str = cbuf.toString();
        write(str);
    }

    @Override
    public void write(int c) throws IOException {
        if (c == '\n') {
            super.write('\n');
            super.write(++nbrLine + "\t");
        } else {
            super.write(c);
        }
    }

}
