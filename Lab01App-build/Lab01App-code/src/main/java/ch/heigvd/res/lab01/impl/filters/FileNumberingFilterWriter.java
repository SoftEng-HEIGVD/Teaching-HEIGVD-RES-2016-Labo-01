package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import ch.heigvd.res.lab01.impl.Utils;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

   private int lineNumber = 1;
   private char lastLetter = (char) 0;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {

      String output = "";
      if (lineNumber == 1) {
         output += lineNumber++ + "\t";
      }

      String strToWrite = str.substring(off, off + len);
      String[] lines = Utils.getNextLine(strToWrite);
      while (!lines[0].equals("")) {
         output += lines[0] + lineNumber++ + "\t";
         lines = Utils.getNextLine(lines[1]);
      }
      output += lines[1];
      super.write(output, 0, output.length());
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      String str = new String(cbuf, off, len);
      this.write(str, off, len);
   }

   @Override
   public void write(int c) throws IOException {

      if (lineNumber == 1) {
         super.write(lineNumber++ + "\t");
      }

      char letter = (char) c;
      if ( ( c == '\n' && lastLetter != '\r' ) || ( c == '\n' && lastLetter == '\r' ) ) {
         super.write(c);
         super.write(lineNumber++ + "\t");
      } else if ( letter != '\n' && lastLetter == '\r' ) {
         super.write(lineNumber++ + "\t");
         super.write(c);
      } else {
         super.write(c);
      }
      lastLetter = letter;
   }

}
