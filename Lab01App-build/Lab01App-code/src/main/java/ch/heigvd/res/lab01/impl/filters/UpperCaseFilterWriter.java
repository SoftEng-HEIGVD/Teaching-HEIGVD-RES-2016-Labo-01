package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * @author Julien Brêchet
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // We need to convert the String in an array of char if we want to use it with an offset and a length
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = 0; i < len; ++i)
      // Uppercase character by caracter
      this.write((int)cbuf[off + i]);
  }

  @Override
  public void write(int c) throws IOException {
    // We simply use the super method of FilterWriter to write the uppercased character
    super.write(Character.toUpperCase(c));
  }

}
