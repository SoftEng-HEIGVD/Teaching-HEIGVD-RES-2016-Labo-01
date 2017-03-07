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

    super.write(str.toUpperCase(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    String test = new String(cbuf);
    write(test,off,len);
  }

  @Override
  public void write(int c) throws IOException {

    String str = String.valueOf((char) c);
    write(str);
  }

}
