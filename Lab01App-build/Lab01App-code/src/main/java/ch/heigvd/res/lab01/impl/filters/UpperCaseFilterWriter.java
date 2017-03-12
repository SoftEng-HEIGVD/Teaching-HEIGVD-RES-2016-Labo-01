package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
    public UpperCaseFilterWriter(Writer wrappedWriter)
    {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException
    {
        //Using the write method of the UpperCaseFilterWriter class
        write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException
    {
        for (int i = off; i < len + off; i++)
        {
            //Using the write method of the UpperCaseFilterWriter class
            write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException
    {
        //Writing in the out attribute of the extended class.
        super.write(Character.toUpperCase(c));
    }
}
