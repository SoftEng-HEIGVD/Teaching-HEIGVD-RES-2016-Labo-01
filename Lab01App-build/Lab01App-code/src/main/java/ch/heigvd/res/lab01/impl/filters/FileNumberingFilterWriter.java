package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.File;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer. When
 * filter encounters a line separator, it sends it to the decorated writer. It then
 * sends the line number and a tab character, before resuming the write process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
   private char charcterBefore = '\0';
   private int lineNumber  = 0;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {

      String cbuf = str.substring(off, off + len);
      for (int i = off; i < off + len; i++) {
         this.write(str.charAt(i));
      }
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      this.write(new String(cbuf), off, len);
   }

   @Override
   public void write(int c) throws IOException {

      if (lineNumber  == 0) {
         out.write(++lineNumber  + "\t");
      }

      if ((char) c == '\n') {
         out.write((char) c);
         out.write(++lineNumber  + "\t");
         charcterBefore = (char) c;
      } else {
         if ( charcterBefore == '\r') { 
            out.write(++lineNumber  + "\t");            
         }
         out.write((char) c);
         charcterBefore = (char) c;
      }
   
   }

      
}
