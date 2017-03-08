package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String result = "";
        for (int i = off; i < len + off; i++) {
            result += Character.toUpperCase(str.charAt(i));
        }
        out.write(result);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        char[] result = new char[len];
        for (int i = 0; i < len; i++) {
            result[i] = Character.toUpperCase(cbuf[i + off]);
        }
        out.write(result);
    }

    @Override
    public void write(int c) throws IOException {
        out.write(Character.toUpperCase(c));
    }

}
