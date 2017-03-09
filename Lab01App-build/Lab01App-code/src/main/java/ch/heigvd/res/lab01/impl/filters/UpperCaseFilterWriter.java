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
    String strUpCase = str.substring(off, off + len).toUpperCase();

    super.write(strUpCase, 0, strUpCase.length());
  }

  @Override
  public void write(String str) throws IOException {
    super.write(str.toUpperCase(), 0, str.length());
  }

  @Override
  public void write(char[] cbuf) throws IOException {
    char[] cbufUpCase = new char[cbuf.length];

    for (int i = 0; i < cbuf.length; i++)
      cbufUpCase[i] = Character.toUpperCase(cbuf[i]);

    super.write(cbufUpCase, 0, cbufUpCase.length);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    char[] cbufUpCase = new char[len];

    for (int i = 0; i < len; i++)
      cbufUpCase[i] = Character.toUpperCase(cbuf[off + i]);

    super.write(cbufUpCase, 0, cbufUpCase.length);
  }

  @Override
  public void write(int c) throws IOException {
    super.write(Character.toUpperCase(c));
  }

}
