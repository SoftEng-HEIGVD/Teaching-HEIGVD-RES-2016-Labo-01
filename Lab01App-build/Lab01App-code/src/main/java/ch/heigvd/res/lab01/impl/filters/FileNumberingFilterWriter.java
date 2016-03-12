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
    private int lineNumber = 1;
    private boolean firstLineNumberWritten = false;
    private boolean previousCharWasLineSeparator = false;
    
    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        
        // handle first line
        if (!firstLineNumberWritten) {
            String line = lineNumber++ + "\t";
            super.write(line, 0, line.length());
            firstLineNumberWritten = true;
        }
        
        // search for lines
        String[] lines = Utils.getNextLine(str.substring(off, off + len));
        while (!lines[0].equals("")) {
            
            // write first line
            super.write(lines[0], 0, lines[0].length());
            
            // write next line number
            String line = lineNumber++ + "\t";
            super.write(line, 0, line.length());
            
            // search for more lines
            str = lines[1];
            lines = Utils.getNextLine(str);
        }
        
        // write remaining text
        super.write(lines[1], 0, lines[1].length());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(int c) throws IOException {
        
        // handle first line number
        if (!firstLineNumberWritten) {
            String line = lineNumber++ + "\t";
            super.write(line, 0, line.length());
            firstLineNumberWritten = true;
        }
        
        // char is line separator
        if (c == '\r' || c == '\n') {
            
            // write it
            super.write(c);
            previousCharWasLineSeparator = true;
        }
        // other chars
        else {
            // if previous char was a line separator, insert line number
            if (previousCharWasLineSeparator) {
                String line = lineNumber++ + "\t";
                super.write(line, 0, line.length());
                previousCharWasLineSeparator = false;
            }
            
            // write it
            super.write(c);
        }
    }
}