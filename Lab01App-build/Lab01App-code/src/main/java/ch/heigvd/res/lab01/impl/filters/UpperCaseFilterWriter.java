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
    //transform str to char.
    write(str.toCharArray(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //only write the offset. Pass it further
    for(int i = off; i<off+len;i++)
      write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {
    //transform the char to capital letter if already not the case and give it so master class.
    out.write(Character.toUpperCase(c));
  }

}
