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

    /**
     * Writes a portion of a string.
     *
     * @param  str  String to be written
     * @param  off  Offset from which to start reading characters
     * @param  len  Number of characters to be written
     *
     * @exception  IOException  If an I/O error occurs
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
            out.write(str.toUpperCase(), off, len);
    }

    /**
     * Writes a portion of a char[].
     *
     * @param  cbuf  String to be written
     * @param  off  Offset from which to start reading characters
     * @param  len  Number of characters to be written
     *
     * @exception  IOException  If an I/O error occurs
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        out.write(String.valueOf(cbuf, off, len).toUpperCase());
    }

     /**
     * Writes an int.
     * 
     * @param c int to be written
     * @throws IOException 
     */
    @Override
    public void write(int c) throws IOException {
        out.write(Character.toUpperCase(c));
    }
}
