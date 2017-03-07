package ch.heigvd.res.lab01.impl.filters;

import java.io.CharArrayReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter
{
  
  public UpperCaseFilterWriter(Writer wrappedWriter)
  {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException
  {
    // Sanity Check
    if (str == null)
      return;

    // Make a substring of the input string
    String substring = str.substring(off, off + len);

    // Convert this substring to an upperCase string
    substring = substring.toUpperCase();

    // Write it to the wrappedWrapper
    super.write(substring, 0, substring.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException
  {
     // Sanity check
     if (cbuf == null) // array not existing
        return;

     if (off + len > cbuf.length) // offset + len is over the size of cbuf
        return;

     for (int i = off; i < off + len; i++)
     {
        char c = cbuf[i];
        write(c);
     }
  }

  @Override
  public void write(int c) throws IOException
  {
     char chr = (char)c;

     // If the character is lowercase, convert it to upperCase
     if (Character.isLowerCase(c))
        c = Character.toUpperCase(c);

     super.write(c);
  }

}
