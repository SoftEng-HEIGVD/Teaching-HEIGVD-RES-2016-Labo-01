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
 */
public class FileNumberingFilterWriter extends FilterWriter
{

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
   private  int counter;
   private  boolean firstCall;
   private boolean previousWasCR;

   public FileNumberingFilterWriter(Writer out)
   {
      super(out);
      counter = 1;
      firstCall = true;
      previousWasCR = false;
   }

   @Override
   public void write(String str, int off, int len) throws IOException
   {
      // Sanity Check
      if (str == null)
         return;

      // Make a substring as required and turn it into a char[]
      String string = str.substring(off, off + len);
      char[] cbuf = string.toCharArray();

      // Send the buffer to the appropriate function
      write(cbuf, 0, cbuf.length);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException
   {
      // Sanity check
      if (cbuf == null)
         return;

      // Send each individual char to the lower level write function
      for (char c : cbuf)
         write(c);
   }

   @Override
   public void write(int c) throws IOException
   {
      // Beginning of line because it's the first call to the write function
      if(firstCall)
      {
         // Add the number and the tab at the beginning
         String str = counter++ + "\t";
         super.write(str, 0, str.length());

         firstCall = false;
      }

      String str;

      // If it's a new line with a \n
      if (c == '\n')
      {
         str = "\n" + counter++ + "\t";
         previousWasCR = false;
         super.write(str, 0, str.length());
      }
      // Otherwise if we have a \r new line and no previous \r
      else if (c == '\r' && !previousWasCR)
      {
         str = "\r";
         previousWasCR = true;
         super.write(str, 0, str.length());
      }
      else
      {
         // If the previous written char was a CR
         if(previousWasCR)
         {
            str = counter++ + "\t";
            super.write(str, 0, str.length());
         }
         super.write(c);
         previousWasCR = false;
      }
   }
}
