package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Xavier Vaz Afonso
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    super.write(str.toUpperCase(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    String str = new String(cbuf);
    write(str,off,len);
  }

  @Override
  public void write(int c) throws IOException {

    String str = String.valueOf((char) c);
    write(str);
  }
}
