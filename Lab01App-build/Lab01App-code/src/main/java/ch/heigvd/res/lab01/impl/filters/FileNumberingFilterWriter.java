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
 * The first 2 methodes were modified to call the last one because we can 
 * always write a single character from a String or an array of char. Thus, 
 * only the last method needed to implement the correct algorithm
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineCounter; // the current line number
    private boolean workOnMac; // \r case

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
        
        // if first line, simply write 1.
        if (lineCounter == 0) {
            lineCounter++;
            out.write(String.valueOf(lineCounter));
            super.write('\t');

        }
        // if we reach a \n, writes it and then writes the line number with \t
        if (c == '\n') {
            super.write(c);
            lineCounter++;
            out.write(String.valueOf(lineCounter));
            super.write('\t');
            if (workOnMac) {
                workOnMac = false;
            }
          
          // if we reach this case, it means we reached a \r but no \n was found
          // after it. we are one character ahead, so we need to first write the
          // line number with the \t and then write the current character.
        } else if (workOnMac) {
            lineCounter++;
            out.write(String.valueOf(lineCounter));
            super.write('\t');
            super.write(c);
            workOnMac = false;

        } else {
            super.write(c);
        }
        // handle \r case
        if (c == '\r') {
            workOnMac = true;
        }
    }
}
