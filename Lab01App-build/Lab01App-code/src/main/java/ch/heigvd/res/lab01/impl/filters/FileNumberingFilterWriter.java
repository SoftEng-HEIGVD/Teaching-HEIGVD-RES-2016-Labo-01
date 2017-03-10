package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineIndex;
    private boolean cR;

    /*
    * Initialising private fields and starting a new line on the underlying writer
    */
    public FileNumberingFilterWriter(Writer out) {
        super(out);
        lineIndex = 1;
        cR = false;
        startNewLine();
    }

    /**
     * Both write methods with String and Char[] arguments call the third version
     * of write with a single character parameter.
     */

    @Override
    public void write(String str, int off, int len) throws IOException {
        this.write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int c = off; c < off + len; c++) {
            this.write((int)cbuf[c]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        if (!isLF(c) && cR) {
            startNewLine();
            cR = false;
        }
        out.write((char) c);
        if (isLF(c)) {
            startNewLine();
            cR = false;
        } else if (isCR(c)) {
            cR = true;
        }
    }

    private boolean isLF(int c) {
        return (char) c == '\n';
    }

    private boolean isCR(int c) {
        return (char) c == '\r';
    }

    /*
    * Starts a new line in the underlying writer following thw specified pattern.
    */
    private void startNewLine() {
        try {
            out.write(lineIndex + "\t");
            lineIndex++;
        } catch (IOException e) {
            LOG.log(Level.SEVERE, "IOException@FileNumberingFilterWriter:startNewLine");
        }
    }
}
