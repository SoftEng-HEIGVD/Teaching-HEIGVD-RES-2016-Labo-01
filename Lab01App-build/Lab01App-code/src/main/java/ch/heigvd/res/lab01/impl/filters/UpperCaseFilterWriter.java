package ch.heigvd.res.lab01.impl.filters;

import java.io.CharArrayReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Dany Simo
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      // up all character in the string from the off and write it 
      super.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      //up each character and put in the table before writing
       for(int i=off; i < len + off; i++) {
         cbuf[i] = Character.toUpperCase(cbuf[i]);
        }
        super.write(cbuf, off, len); 
  }

  @Override
  public void write(int c) throws IOException {
      //up the character
        super.write(Character.toUpperCase(c));
  }

}
