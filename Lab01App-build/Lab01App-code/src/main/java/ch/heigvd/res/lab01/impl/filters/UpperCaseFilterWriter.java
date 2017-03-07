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
    String upper = str.toUpperCase();
    out.write(upper,off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      char[] upper = new char[cbuf.length];

      for(int i=0;i<cbuf.length;i++){
          upper[i]=Character.toUpperCase(cbuf[i]);
      }

      out.write(upper,off,len);
  }

  @Override
  public void write(int c) throws IOException {
      out.write(Character.toUpperCase(c));
  }

}
