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
 * @author Baeriswyl Julien (julien.baeriswyl@heig-vd.ch) [MODIFIED BY, on 2017-03-12]
 */
public class FileNumberingFilterWriter extends FilterWriter
{
    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    
    private int lineCounter;
    private boolean newline = true;
    private int lastChar = -1;

    /**
     * Write line number and update flags.
     *
     * @throws IOException if write operations fail
     */
    private void writeLineNumber () throws IOException
    {
        // JBL: write formatted line number
        String countStr = String.format("%d\t", ++lineCounter);
        super.write(countStr, 0, countStr.length());

        // JBL: update flags
        lastChar = -1;
        newline  = false;
    }
    
    public FileNumberingFilterWriter (Writer out)
    {
        super(out);
    }

    @Override
    public void write (String str, int off, int len) throws IOException
    {
        // JBL: ensure no reading outside array range
        len = Math.min(len, str.length() - off);

        // JBL: iterate char by char, with call to overloaded/overridden 'write (int c)'
        for (int i = 0; i < len; ++i)
        {
            write(str.charAt(off + i));
        }

        // JBL: even if last new line is empty, we number it
        if (newline)
        {
            writeLineNumber();
        }
    }

    @Override
    public void write (char[] cbuf, int off, int len) throws IOException
    {
        // JBL: ensure no reading outside array range
        len = Math.min(len, cbuf.length - off);

        // JBL: iterate char by char, with call to overloaded/overridden 'write (int c)'
        for (int i = 0; i < len; ++i)
        {
            write(cbuf[off + i]);
        }

        // JBL: even if last new line is empty, we number it
        if (newline)
        {
            writeLineNumber();
        }
    }

    @Override
    public void write (int c) throws IOException
    {
        // JBL: write new line if we are not in the middle of combinations "\r\n" or "\n\r"
        if (newline && !(c != lastChar && (c == '\n' | c == '\r')))
        {
            writeLineNumber();
        }
        else if (c == '\n' | c == '\r') // JBL: update flags if new line char encountered
        {
            lastChar = c;
            newline  = true;
        }

        super.write(c);
    }
}
