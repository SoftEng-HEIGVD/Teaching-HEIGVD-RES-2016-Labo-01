package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti, Basile Châtillon
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // we convert the string in an array of char
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // we iterate on the array to apply on each char the write(int ) methode
    for(int i = off ; i < len + off ; ++i){
      write((int) cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // we just transform the char to his uppercase and write it
    out.write(Character.toUpperCase(c));
  }

}
