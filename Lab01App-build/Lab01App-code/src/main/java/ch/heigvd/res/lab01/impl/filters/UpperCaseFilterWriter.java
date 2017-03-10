package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 
 * @author Olivier Liechti
 * @author Denise Gemesio
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  // Transforms the string in a char array and calls the function which takes a char array
  // as parameter
  @Override
  public void write(String str, int off, int len) throws IOException {
     write(str.toCharArray(), off, len);
  }
  
  // Calls the function which takes an integer as parameter and gives it one char by char
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     for (int i = 0; i < len; ++i) {
        write(cbuf[i + off]);
     }
  }

  // Invokes the function toUpperCase on a char
  @Override
  public void write(int c) throws IOException {
     out.write(Character.toUpperCase((char)c));
  }

}
