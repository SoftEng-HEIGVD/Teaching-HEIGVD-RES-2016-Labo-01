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


  // This methode transform the str to un uppercase and write it
  // the goal is to do the both action with the call of one methode.
  @Override
  public void write(String str, int off, int len) throws IOException
  {
    str = str.toUpperCase();
    super.write(str, off, len);
  }

  // For this methode I didn't call the wirte with the string
  // because the toUpercase will do the same as me but will need to create a string
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException
  {
    for (int i = 0; i < cbuf.length; ++i)
    {
      cbuf[i] = Character.toUpperCase(cbuf[i]);
    }

    super.write(cbuf, off, len);
  }

  // just call the super write methode with a upercase
  @Override
  public void write(int c) throws IOException
  {
    super.write(Character.toUpperCase(c));
  }

}
