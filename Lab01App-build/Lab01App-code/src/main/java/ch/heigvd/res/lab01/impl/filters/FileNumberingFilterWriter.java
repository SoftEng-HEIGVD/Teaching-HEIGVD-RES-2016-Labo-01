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
public class FileNumberingFilterWriter extends FilterWriter
{

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int currentLineNumber = 0;
    private boolean precedentIsCarriageReturn = false;

    public FileNumberingFilterWriter(Writer out)
    {
        super(out);
    }


    /**
     * We check each char (int) passed as parameter, and :
     * 1) if it's not a separator (not \r, neither \n, nor \r\n), writes it
     * 2) if it's a separator, writes it, then the line number, then a <tab>
     *     to check the second, we made a pseudo FSM :
     *
     * @param c the char (as an int) to check and write
     * @throws IOException
     */
    @Override
    public void write(int c) throws IOException
    {
        if (c )
        out.write(c);
    }


    @Override
    public void write(String str, int off, int len) throws IOException
    {
        throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }


}
