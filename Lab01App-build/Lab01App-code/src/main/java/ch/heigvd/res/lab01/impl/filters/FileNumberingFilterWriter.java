package ch.heigvd.res.lab01.impl.filters;

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

   private int finalChar;
   private int lineNumber;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
      write(str.toCharArray(), off, len);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      for (int i = 0; (i < len) && (i + off < cbuf.length); ++i) {
         write(cbuf[off + i]);
      }
   }

   @Override
   public void write(int c) throws IOException {
      if (lineNumber == 0) {
         ++lineNumber;
         super.write('1');
         super.write('\t');
      }
      // Detection of the end of line for Unix and Windows
      if (c == '\n') {
         super.write(c);
         // convert the integer into a string and write the result
         super.write(String.valueOf(++lineNumber).charAt(0));
         super.write('\t');

      } else if (finalChar == '\r') { // Detection of the end of line for MacOS
         super.write(String.valueOf(++lineNumber).charAt(0));
         super.write('\t');
         super.write(c);

      } else {
         super.write(c);
      }
      // in case of \r\n
      finalChar = c;
   }

}
