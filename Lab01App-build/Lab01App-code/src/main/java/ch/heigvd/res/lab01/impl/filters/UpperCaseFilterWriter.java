package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;

/**
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String sub = str.substring(off, off + len);
        out.write(sub.toUpperCase());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        char[] buf = Arrays.copyOfRange(cbuf, off, off + len);
        String str = new String(buf);
        out.write(str.toUpperCase());
    }

    @Override
    public void write(int c) throws IOException {
        out.write(Character.toUpperCase(c));
    }

}
