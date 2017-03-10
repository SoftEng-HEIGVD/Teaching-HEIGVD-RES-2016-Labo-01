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
   private boolean wasPreviouslyR = false; // indicates if the previous char was a CR
   private int nbOfLines = 0;             // current line number

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
      write(str.toCharArray(), off, len);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      for (int i = off; i < off + len; i++) {
         write(cbuf[i]);
      }
   }

   @Override
   public void write(int c) throws IOException {
      // at the beginning writes a new line
      if (nbOfLines == 0) {
         out.write("1\t");
         nbOfLines++;
      }

      // writes new line if previously it was a CR and now not a LF
      if (wasPreviouslyR && c != '\n') {
         out.write(Integer.toString(++nbOfLines) + '\t');
      }

      // writes the char 
      out.write(c);

      // checks if it is a CR char
      wasPreviouslyR = (c == '\r');

      // finally writes a new line if it is a LF
      if (c == '\n') {
         out.write(Integer.toString(++nbOfLines) + '\t');
      }

   }

}
