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
    super.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    char[] localCBuf = new char[len];
    for(int i = 0; i < len; ++i)
      localCBuf[i] = Character.toUpperCase(cbuf[off + i]);
    super.write(localCBuf, 0, len);
  }

  @Override
  public void write(int c) throws IOException {
    //no check is done here to confirm if c is really a char
    super.write(Character.toUpperCase(c));
  }

}
