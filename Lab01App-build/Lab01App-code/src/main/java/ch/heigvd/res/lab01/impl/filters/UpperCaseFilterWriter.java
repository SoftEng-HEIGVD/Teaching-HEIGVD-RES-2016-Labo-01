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
  
  @Override
  public void write(String str, int off, int len) throws IOException {
      /*Call the function write with char[] in param*/
      write(str.toCharArray(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      /*for each character from offset(off) and lenght(len) in cbuf,
      we call the function write which convert one character*/
      for(int i = off ; i < off + len ; i ++) 
      {
          write((int)cbuf[i]);
      }      
  }

  @Override
  public void write(int c) throws IOException {
      /*Convert one character lowercase in Uppercase*/
      super.write(Character.toUpperCase(c));
  }

}
