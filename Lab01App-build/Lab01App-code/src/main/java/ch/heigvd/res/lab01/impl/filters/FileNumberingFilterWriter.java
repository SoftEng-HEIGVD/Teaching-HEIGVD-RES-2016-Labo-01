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
    
    public FileNumberingFilterWriter (Writer out)
    {
        super(out);
    }

    @Override
    public void write (String str, int off, int len) throws IOException
    {

        String[] lines = Utils.getNextLine(str.substring(off, off + len));
        String countStr;

        while (true)
        {
            if (newline)
            {
                countStr = String.format("%d\t", ++lineCounter);
                super.write(countStr, 0, countStr.length());
                newline = false;
            }

            if (lines[0].isEmpty())
            {
                super.write(lines[1], 0, lines[1].length());
                return;
            }

            super.write(lines[0], 0, lines[0].length());
            lines  = Utils.getNextLine(lines[1]);
            newline = true;
        }
    }

    @Override
    public void write (char[] cbuf, int off, int len) throws IOException
    {
        /* DONE */
        write(String.copyValueOf(cbuf, off, len), 0, len);
    }

    @Override
    public void write (int c) throws IOException
    {
        /* DONE */
        if ((c == '\n' | c == '\r'))
        {
            newline = true;
        }
        else if (newline)
        {
            String countStr = String.format("%d\t", ++lineCounter);
            super.write(countStr, 0, countStr.length());
            newline = false;
        }

        super.write(c);
    }
}
