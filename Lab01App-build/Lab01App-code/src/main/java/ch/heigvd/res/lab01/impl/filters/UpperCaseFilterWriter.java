package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Makes every string uppercase
 *
 * @author Mathieu Urstein
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        out.write(str.substring(off, off+len).toUpperCase());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        // for every requested char
        for (int i = 0; i < len; i++) {
            // make it uppercase
            cbuf[off+i] = Character.toUpperCase(cbuf[off+i]);
            out.write(cbuf[off+i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        // just write uppercase version of c
        out.write(Character.toUpperCase(c));
    }

}
