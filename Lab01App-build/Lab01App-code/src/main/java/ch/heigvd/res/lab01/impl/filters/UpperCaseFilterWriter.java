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
    String tmp = str.substring(off, off + len);
    out.write(tmp.toUpperCase());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    char[] tmp = new char[len];
    System.arraycopy(cbuf, off, tmp, 0, len);

    char[] tmpUpperCased = new char[len];
    for(int i = 0; i < len; i++) {
      tmpUpperCased[i] = Character.toUpperCase(tmp[i]);
    }
    out.write(tmpUpperCased);
  }

  @Override
  public void write(int c) throws IOException {
    out.write(Character.toUpperCase(c));
  }

}
