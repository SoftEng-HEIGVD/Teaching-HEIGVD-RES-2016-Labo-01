package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @author Olivier Liechti
 * @modified by Matthieu Chatelan
 */
public class UpperCaseFilterWriter extends FilterWriter
{
   public UpperCaseFilterWriter(Writer wrappedWriter)
   {
      super(wrappedWriter);
   }

   /**
    * This function allows us to override the write function. Here we
    * modify the string given in parameters to apply a simple transformation
    * turning the string in an all CAPS sentence and writing it to the wrapperWrapper.
    *
    * @param str the string to turn to CAPS
    * @param off the offset from where to start writing
    * @param len the number of chars we take
    * @throws IOException
    */
   @Override
   public void write(String str, int off, int len) throws IOException
   {
      // Sanity Check
      if (str == null)
         return;
      if (off + len > str.length())
         return;

      // Make a substring of the input string
      String substring = str.substring(off, off + len);

      // Convert this substring to an upperCase string
      substring = substring.toUpperCase();

      // Write it to the wrappedWrapper
      super.write(substring, 0, substring.length());
   }

   /**
    * Does the same as above, turning a "string" (here a char[]) into an all CAPS char[]
    * and writing it to the wrappedWriter
    *
    * @param cbuf the buffer to work on
    * @param off  the offset where we start
    * @param len  the number of chars we convert
    * @throws IOException
    */
   @Override
   public void write(char[] cbuf, int off, int len) throws IOException
   {
      // Sanity check
      if (cbuf == null) // array not existing
         return;
      if (off + len > cbuf.length)
         return;

      // Send each individual character to the write function declared bellow
      for (int i = off; i < off + len; i++)
      {
         char c = cbuf[i];
         write(c);
      }
   }

   /**
    * Turn the character to an upperCase character and write it to the wrappedWriter
    *
    * @param c the character to convert
    * @throws IOException
    */
   @Override
   public void write(int c) throws IOException
   {
      // If the character is lowercase, convert it to upperCase
      if (Character.isLowerCase(c))
         c = Character.toUpperCase(c);

      // Write the character to the wrappedWriter
      super.write(c);
   }

}
