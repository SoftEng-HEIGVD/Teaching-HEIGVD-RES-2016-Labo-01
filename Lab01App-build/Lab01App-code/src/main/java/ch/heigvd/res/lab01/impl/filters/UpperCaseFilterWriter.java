package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti, Basile Vu
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // Writes the char array from the string using our write function to write char arrays.
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // Writes 'len' chars, starting char at pos 'off', using our write method for chars.
    // If the offset is not smaller than the buffer length, we stop.
    for (int i = off; i < cbuf.length && i < off + len; ++i) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // Directly writes the character to upper case.
    out.write(Character.toUpperCase(c));
  }

}
