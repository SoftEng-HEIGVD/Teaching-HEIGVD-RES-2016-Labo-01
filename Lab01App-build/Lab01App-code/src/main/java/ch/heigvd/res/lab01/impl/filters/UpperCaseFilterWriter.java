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

    /*
     *  transform the string to upper case and writes it
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        super.write(str.toUpperCase(), off, len);
    }

    /*
     *  transform char array into string and then put it to upper case then writes it
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        super.write(new String(cbuf).toUpperCase(), off, len);
    }

    /*
     *  transform int into string and then put it to upper case then writes it
     */
    @Override
    public void write(int c) throws IOException {
        //transforms int into char, but takes only the first char
        Character a = Character.toChars(c)[0];
        super.write(a.toString().toUpperCase());
    }

}
