package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Nathan Gonzalez Montes
 */
public class UpperCaseFilterWriter extends FilterWriter {
    
    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }
    
    /**
     * Writes a portion of a string in Upper Case
     * @param str String to be written
     * @param off Offset from witch to start reading characters
     * @param len Number of characters to be written
     * @throws IOException 
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        str = str.toUpperCase();
        super.write(str, off, len);
    }
    
    /**
     * Writes a portion of an array of characters in Upper Case
     * @param cbuf Buffer of characters to be written
     * @param off Offset from witch to start reading characters
     * @param len Number of characters to be written
     * @throws IOException 
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = off; i < off + len; ++i){
            cbuf[i] = Character.toUpperCase(cbuf[i]);
        }
        super.write(cbuf, off, len);
    }
    
    /**
     * Writes char by char in Upper Case passing an int by parameter
     * @param c the current character to Upper Case
     * @throws IOException 
     */
    @Override
    public void write(int c) throws IOException {
        c = Character.toUpperCase((char)c);
        super.write(c);
    }
}
