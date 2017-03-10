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

    //Changes the string into charArray and send it to write(char[] cbuf, ...)
    write(str.toCharArray(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    //Reads the char array and write char by char with write(int c)
    for(int i = off; i < len + off; ++i)
    {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    //Writes the char in upperCase
    out.write(Character.toUpperCase(c));
  }
}
