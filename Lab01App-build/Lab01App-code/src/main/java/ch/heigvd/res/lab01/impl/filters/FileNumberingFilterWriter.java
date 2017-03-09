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
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter
{

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int currentLineNumber = 0;
    private boolean precedentIsCarriageReturn = false;
    private boolean isFirstLine = true;

    public FileNumberingFilterWriter(Writer out)
    {
        super(out);
    }


    /**
     * We check each char (int) passed as parameter, and :
     * 1) if it's the first line : decorate.
     * 2) if it's a \r, wait for next char
     * 3) if it's a \n,
     * if it's not a separator (not \r, neither \n, nor \r\n), writes it
     * 2) if it's a separator, writes it, then the line number, then a <tab>
     * to check the second, we made a pseudo FSM :
     *
     * @param c the char (as an int) to check and write
     * @throws IOException
     */
    @Override
    public void write(int c) throws IOException
    {
        if (isFirstLine) {
            out.write(Integer.toString(++currentLineNumber));
            out.write('\t');
            out.write(c);
            isFirstLine = false;
            return;
        }

        if (c == '\r')
        {
            precedentIsCarriageReturn = true;
            return;
        }
        else if (c == '\n')
        {
            // \n following a \r, add the precedent \r
            if (precedentIsCarriageReturn)
            {
                out.write('\r'); // write the precedent carriage return

                precedentIsCarriageReturn = false; // reset the state variable
            }
            // in all the cases, decorate as specified
            out.write(c);
            out.write(Integer.toString(++currentLineNumber));
            out.write('\t');
            return;
        }
        else // not \n, not \r, so not \r\n
        {
            if (precedentIsCarriageReturn)
            {
                out.write('\r'); // write the precedent carriage return
                out.write(Integer.toString(++currentLineNumber));
                out.write('\t');

                precedentIsCarriageReturn = false; // reset the state variable
            }

            out.write(c);

        }

    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        for (int currentCharIndex = off ; currentCharIndex < off + len; ++currentCharIndex)
        {
            this.write((int) cbuf[currentCharIndex]);
        }
    }


    @Override
    public void write(String str, int off, int len) throws IOException
    {
        for (int currentCharPos = off; currentCharPos < off + len; ++currentCharPos) {
            this.write(str.codePointAt(currentCharPos));
        }
    }




}
