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
      // Modifying the string before passing it to the writer
      String toWrite = str;
      if (len != 0)
      {
          toWrite = str.substring(off, off + len);
      }
       
      out.write(toWrite.toUpperCase());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String toWrite = new String(cbuf);
    if (len != 0)
    {
        toWrite = toWrite.substring(off, off + len);
    }
    out.write(toWrite.toUpperCase());
  }

  @Override
  public void write(int c) throws IOException {
    String toWrite = (char)c + "";  
    out.write(toWrite.toUpperCase());
  }

}
