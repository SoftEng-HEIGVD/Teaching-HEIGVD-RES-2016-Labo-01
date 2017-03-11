package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Mathias Gilson
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        // Change the string into a charArray and send to corresponding method
        write(str.toCharArray(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // call the write(int c) method for each char in the charArray
        for(int i = off; i < len + off; i++)
            write(cbuf[i]);
    }

    @Override
    public void write(int c) throws IOException {
        // use the standard toUpperCase method
        out.write(Character.toUpperCase(c));
    }

}
