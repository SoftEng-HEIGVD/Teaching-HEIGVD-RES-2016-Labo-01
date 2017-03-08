package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti 
 * Modified by Lara Chauffoureaux on 08.03.2017
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {       
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        String upperStr = str.toUpperCase();

        // i use the wrapped writer write methods, thus i can call directly have 
        // the version that suits me
        out.write(upperStr, off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        char[] upperCbuf = new char[cbuf.length];

        for (int i = 0; i < cbuf.length; i++) {

            upperCbuf[i] = Character.toUpperCase(cbuf[i]);
        }

        out.write(upperCbuf, off, len);
    }
    
    @Override
    public void write(int c) throws IOException {

        out.write(Character.toUpperCase((char) c));
    }
}
