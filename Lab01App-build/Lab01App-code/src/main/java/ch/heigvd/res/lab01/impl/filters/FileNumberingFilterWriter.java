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
    private int linesWritten;

    public FileNumberingFilterWriter(Writer out)
    {
        super(out);
        linesWritten = 0;
    }

    @Override
    public void write(String str, int off, int len) throws IOException
    {                
        final String TAB = "\t";
        str = str.substring(off, off + len);
        
        String[] lines = Utils.getNextLine(str);
        
        //if it's the first time we write, we must provide the first line number
        if (linesWritten == 0)
        {
            String temp = ++linesWritten + TAB;
            super.write(temp.toCharArray(), 0, temp.length());
        }
        
        //We consume the string until we reach the end of it
        //We write new lines until we don't find any separators anymore
        while (!lines[0].isEmpty())
        {
            String line = lines[0] + (++linesWritten) + TAB;
            super.write(line.toCharArray(), 0, line.length());
            lines = Utils.getNextLine(lines[1]);
        }
        
        //We write the rest of the string wich can be separatorless
        if(!lines[1].isEmpty())
            super.write(lines[1].toCharArray(), 0, lines[1].length());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {        
        String lines = String.valueOf(cbuf);
        write(lines, off, len);
    }

    @Override
    public void write(int c) throws IOException
    {      
        char[] buf = Character.toChars(c);
        write(buf, 0, 1);
    }

}
