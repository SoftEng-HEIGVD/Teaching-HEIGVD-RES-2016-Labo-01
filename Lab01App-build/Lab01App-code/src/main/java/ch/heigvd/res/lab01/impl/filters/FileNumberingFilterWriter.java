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
   private static int counter = 1;
   private static boolean firstCall = true;

   public FileNumberingFilterWriter(Writer out)
   {
      super(out);
      counter = 1;
      firstCall = true;
   }

   @Override
   public void write(String str, int off, int len) throws IOException
   {
      // Sanity Check
      if (str == null)
         return;

      String result;

      // If the string contain a \n or a \r in the middle, jump to the appropriate write function
      /*if (str.indexOf('\n') != str.length() - 1 || str.indexOf('\r') != str.length()-1)
      {
         write(str.toCharArray(), off, len);
         return;
      }*/

      // If it's the first call to the method, we have to put the number in front
      if (firstCall)
      {
         result = counter++ + "\t" + str.substring(off, off + len);
         firstCall = false;
      }
      else // Just add the string without modification
      {
         result = str.substring(off, off + len);
      }

      // We add the formatted string to the Writer
      super.write(result, 0, result.length());

      // If there is a new line at the end, add the next number bellow
      if (str.contains("\r") || str.contains("\n"))
      {
         String nextLine = counter++ + "\t";
         super.write(nextLine, 0, nextLine.length());
      }
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException
   {
      // Sanity check
      if (cbuf == null)
         return;

      for (char c : cbuf)
         write(c);
   }

   @Override
   public void write(int c) throws IOException
   {
      String result;

      // If it's the first call to the method, add the line number at the beginning
      if (firstCall)
      {
         result = counter++ + "\t";
         super.write(result, 0, result.length());
         firstCall = false;
      }

      // If the last char is a return, add it and the new counter
      if (c == '\n')
      {
         result = '\n' + counter++ + "\t";
         super.write(result, 0, result.length());
      }
      // If it's a regular char, add it
      else
      {
         // Write the char to the output
         super.write(c);
      }
   }
}
