package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;

/**
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {

    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        //Get the length of the substring
        int endIndex = off + len;
        String upper;
        //If the end index is more or equal of the length of the string, set it to the end of the string. Also
        // transform it to uppercase
        if(off + len >= str.length()){
            upper = str.substring(off).toUpperCase();
        }
        else {
            upper = str.substring(off, endIndex).toUpperCase();
        }
        try{
            out.write(upper);
        }catch(IOException e){
            throw e;
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(new String(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        write(Character.toString((char)c), 0, 1);
    }

}
