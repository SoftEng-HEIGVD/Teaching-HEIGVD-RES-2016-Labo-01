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
     /*we convert the tab of char into string and we call  our method write */
     String str = new String(cbuf);
     this.write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {
     /*we convert the character to UpperCase and we call write of the heritate class*/
    super.write(Character.toUpperCase(c));
  }

}
