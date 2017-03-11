package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author by Matthieu Chatelan
 */
public class FileNumberingFilterWriter extends FilterWriter
{
   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

   // The current line number
   private int counter;

   // Determine whether it's the first call to the write function or not
   private boolean firstCall;

   // A flag to determine if the last char written was a \r (in the case we have the \r\n)
   private boolean previousWasCR;

   public FileNumberingFilterWriter(Writer out)
   {
      super(out);
      counter = 1;
      firstCall = true;
      previousWasCR = false;
   }

   /**
    * Overwrite writer to allow us to add the line number to the writer
    * @param str the string to which we have to add a line number
    * @param off from where to start to take the string
    * @param len how many char we take
    * @throws IOException
    */
   @Override
   public void write(String str, int off, int len) throws IOException
   {
      // Sanity Check
      if (str == null)
         return;
      // Check if the offset and length go beyond the limit of str
      if (off + len > str.length())
         return;

      // Make a substring as required and turn it into a char[]
      String string = str.substring(off, off + len);
      char[] cbuf = string.toCharArray();

      // Send the buffer to the appropriate function (to avoid code duplication)
      write(cbuf, 0, cbuf.length);
   }

   /**
    * Does the same as above, except it's not a string but a char array. This method is called by the one above
    * @param cbuf the char array to transform
    * @param off where to start to transform
    * @param len how many char to take
    * @throws IOException
    */
   @Override
   public void write(char[] cbuf, int off, int len) throws IOException
   {
      // Sanity check
      if (cbuf == null)
         return;

      // Send each individual char to the write(int c) function
      for (char c : cbuf)
         write((int) c);
   }

   /**
    * Does the same as above, but with only one char sent. This method is called by the one above
    * @param c the character to which we might need to add a number in front
    * @throws IOException
    */
   @Override
   public void write(int c) throws IOException
   {
      String str;

      // Beginning of line because it's the first call to the write function, so add the line number and tab
      if (firstCall)
      {
         // Add the number and the tab at the beginning
         str = counter++ + "\t";
         super.write(str, 0, str.length());

         // We are not longer at the beginning of the file
         firstCall = false;
      }

      // If it's a new line with a \n
      if (c == '\n')
      {
         str = "\n" + counter++ + "\t";
         previousWasCR = false;
         super.write(str, 0, str.length());
      }
      // Otherwise if we have a \r
      else if (c == '\r')
      {
         str = "\r";
         previousWasCR = true;
         super.write(str, 0, str.length());
      }
      // All other characters
      else
      {
         // If the previous written char was a CR \r and now we have a letter, we have to write
         // the line number and add a tab character
         if (previousWasCR)
         {
            str = counter++ + "\t";
            super.write(str, 0, str.length());
         }

         // We write the character to the writer.
         super.write(c);

         // Disable the flag because we just wrote a normal char
         previousWasCR = false;
      }
   }
}
