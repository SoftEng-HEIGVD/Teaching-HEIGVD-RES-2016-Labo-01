package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * CLASS: Apply uppercase transformation on each character about to be written in original writer.
 *
 * @author Olivier Liechti
 * @author Baeriswyl Julien (julien.baeriswyl@heig-vd.ch) [MODIFIED BY, on 2017-03-10]
 */
public class UpperCaseFilterWriter extends FilterWriter
{
    public UpperCaseFilterWriter (Writer wrappedWriter)
    {
        super(wrappedWriter);
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
    }

    @Override
    public void write (int c) throws IOException
    {
        // JBL: apply uppercase transformation, before writing char
        super.write(Character.toUpperCase(c));
    }
}
