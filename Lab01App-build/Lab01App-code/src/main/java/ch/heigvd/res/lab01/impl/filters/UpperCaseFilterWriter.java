package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        out.write(str.toUpperCase(), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        // Call the write fonction "write(int c" for each char in the array.
        String tmp = "";
        int i = 0;
        while (i < len) {
            tmp += cbuf[i + off];
            i++;
        }
        this.write(tmp);
    }

    @Override
    public void write(int c) throws IOException {
        out.write(Character.toUpperCase(c));
    }
}
