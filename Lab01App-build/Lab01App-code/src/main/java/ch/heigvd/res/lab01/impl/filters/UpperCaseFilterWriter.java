package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

/**
 * @author Olivier Liechti
 * @modifiedBy Ali Miladi
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        out.write(str.toUpperCase(Locale.ROOT), off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        char[] outbuf = new char[cbuf.length];
        for (int i = off; i < off + len; i++) {
            outbuf[i] = Character.toUpperCase(cbuf[i]);
        }
        out.write(outbuf, off, len);
    }

    @Override
    public void write(int c) throws IOException {
        out.write(Character.toUpperCase(c));
    }

}
