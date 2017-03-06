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
     String tmp = str.substring(off,off+len);
     String tmp2 = tmp.toUpperCase();
     
     out.write(tmp2);
     
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     String tmp = new String(cbuf);
     String tmp2 = tmp.substring(off, off+len);
     String tmp3 = tmp2.toUpperCase();
     
     out.write(tmp3);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
     int tmp = Character.toUpperCase(c);
     out.write(tmp);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

}
