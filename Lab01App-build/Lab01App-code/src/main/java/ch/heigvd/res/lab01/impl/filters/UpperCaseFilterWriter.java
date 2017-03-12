package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Damien Carnal
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
    public UpperCaseFilterWriter(Writer wrappedWriter) {
        super(wrappedWriter);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        
        if (str == null){
            return;
        }
        if (off + len > str.length()){
            return;
        }

        String substring = str.substring(off, off + len);
        substring = substring.toUpperCase();
        super.write(substring, 0, substring.length());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if (cbuf == null){
            return;
        }
        if (off + len > cbuf.length){
            return;
        }

        for (int i = off; i < off + len; i++){
            char c = cbuf[i];
            write(c);
        }
    }

    @Override
    public void write(int c) throws IOException {
        // conversion en majuscule
        if (Character.isLowerCase(c)){
            c = Character.toUpperCase(c);
        }
        super.write(c);
    }

}
