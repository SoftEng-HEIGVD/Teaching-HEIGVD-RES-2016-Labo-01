package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Pierre-Benjamin Monaco
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    str.toUpperCase();
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = 0; i < cbuf.length; ++i)
    {
      cbuf[i] = Character.toUpperCase(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if((int)'a' >= c || (int)'z' <= c)
    {
      c += (int)'A' - (int)'a';
    }
  }
}
