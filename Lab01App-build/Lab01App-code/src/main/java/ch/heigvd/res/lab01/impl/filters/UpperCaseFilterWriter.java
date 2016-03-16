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
      // simply transform the String to upperCase and write it
      String upperCaseString = str.toUpperCase();
      super.write(upperCaseString, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      // perform an upperCase transformation on every character
      char[] upperCaseBuf = new char[cbuf.length];
      for(int i = 0; i < cbuf.length; i++) {
          upperCaseBuf[i] = Character.toUpperCase(cbuf[i]);
      }
      super.write(upperCaseBuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
      // perform and upperCase transformation on a char
      super.write(Character.toUpperCase(c));
  }

}
