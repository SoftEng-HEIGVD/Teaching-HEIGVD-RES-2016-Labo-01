/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : 1
 File         : UpperCaseFilterWriter.java
 Author       : Antoine Drabble
 Date         : 10.03.2016
 -----------------------------------------------------------------------------------
*/
package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Change every letter written by a decorated writer to uppercase
 * 
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    /**
     * Write a string in uppercase
     * 
     * @param str
     * @param off
     * @param len
     * @throws IOException 
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        out.write(str.substring(off, off + len).toUpperCase());
    }

    /**
     * Write a char array in uppercase
     * 
     * @param str
     * @param off
     * @param len
     * @throws IOException 
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = off; i < off + len; i++){
            out.write(Character.toUpperCase(cbuf[i]));
        }
    }

    /**
     * Write a char in uppercase
     * 
     * @param c
     * @throws IOException 
     */
    @Override
    public void write(int c) throws IOException {
        out.write(Character.toUpperCase(c));
    }

}
