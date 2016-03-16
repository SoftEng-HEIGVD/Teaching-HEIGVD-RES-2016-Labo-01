package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
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
   private int count = 0;
   private char previousWrittenCharacter = '\0';

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {

      if (count == 0) {
         out.write(++count + "\t");
      }
      String[] lines = Utils.getNextLine(str.substring(off, off + len));

      while (true) {
         if (lines[1].isEmpty() && !lines[0].isEmpty()) {
            out.write(lines[0] + ++count + "\t");
            lines[0] = "";
         }
         if (lines[0].isEmpty() && !lines[1].isEmpty()) {
            out.write(lines[1]);
            lines[1] = "";
         }
         if (!lines[0].isEmpty() && !lines[1].isEmpty()) {
            out.write(lines[0] + ++count + "\t");
            lines = Utils.getNextLine(lines[1]);
         }
         if (lines[0].isEmpty() && lines[1].isEmpty()) {
            break;
         }
      }
      
     /* We could also use the following instruction to achieve the required out put
      for(int i = off; i < off + len; ++i)
         this.write(str.charAt(i));
             */

   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      this.write(new String(cbuf), off, len);
   }

   @Override
   public void write(int c) throws IOException {
      /*
      The principal difficulty in this override is to be able to determine that the input string 
      contains either '\n' , '\r' or "\r\n" , especially the "\r\n".
      for this reason i have declared a variable that gives me the state of the previously
      writen character 
      */
      
      char _c;
      _c = (char) c;

      if (count == 0) {
         out.write(++count + "\t");
      }

      if (_c == '\n') {
         out.write(_c);
         out.write(++count + "\t");
         previousWrittenCharacter = _c;
      } else {
         if ( previousWrittenCharacter == '\r') { // knowing that current character is different from '\n',
            out.write(++count + "\t");           // if the previous character was '\r' we number the line 
         }
         out.write(_c);
         previousWrittenCharacter = _c;
      }
   }
}
