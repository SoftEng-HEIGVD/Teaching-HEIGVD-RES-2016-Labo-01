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
    private int currentLine = 1;
    private boolean firstCall = true;
    private int lastChar = -1;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        this.write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = 0; i < len; i++) {
            //We call write(c) to have a factorized code
            this.write((int) cbuf[off + i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        //If it is the first call, we have to write the number
        if (firstCall) {
            out.write(currentLine + "\t");
            out.write(c);
            firstCall = false;
        } else { //It's not the first call
            //MacOS 9
            if (lastChar == '\r' && c != '\n') {
                out.write(++currentLine + "\t");
            }

            out.write(c);
            
            //Other OS
            if (c == '\n') { 
                out.write(++currentLine + "\t");
            }
        }

        //Updates
        lastChar = c;
    }

}
