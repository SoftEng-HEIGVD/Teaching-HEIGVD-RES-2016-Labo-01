package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Aurelie Levy
 */
public class UpperCaseFilterWriter extends FilterWriter {
    
    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }
    
    /**
     * call write(char[] cbuf, int off, int len). Allow to write an array of char with a filter
     * @param str string to use
     * @param off offset
     * @param len length from the offset to use (for the string str)
     * @throws IOException 
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        
        for (int i = off; i < off + len; i++) {
            this.write(str.charAt(i));
        }

        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
    
    /**
     * Allow to write an array of char with a filter. Call write(int c)
     * @param cbuf array of character
     * @param off offset
     * @param len length from the offset to use (for the string str)
     * @throws IOException 
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        
        String str = "";
        for(int i = 0; i < cbuf.length; i++){
            str += cbuf[i];
        }
        write(str, off, len);
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
    
    /**
     * Method used by the other write. Apply the filter (here: use uppercase for each character)
     * @param c character
     * @throws IOException 
     */
    @Override
    public void write(int c) throws IOException {
        
        c = Character.toUpperCase(c);//put the character in uppercase
        out.write(c); //write the character
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    }
    
}
