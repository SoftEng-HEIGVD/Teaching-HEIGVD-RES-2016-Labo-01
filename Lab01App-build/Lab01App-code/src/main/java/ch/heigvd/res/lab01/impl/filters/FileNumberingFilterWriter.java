package ch.heigvd.res.lab01.impl.filters;

import static ch.heigvd.res.lab01.impl.Utils.getNextLine;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

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
   private int line = 1;
   private boolean firstLine = true;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
      String[] sTab = getNextLine(str.substring(off, off + len));
      if (firstLine == true) {
         out.write(line++ + "\t"); // this is a counter for current line we are working on
         firstLine = false;
      }
      while (sTab[0].length() != 0) { // same thing with others lines
         out.write(sTab[0] + line++ + "\t");
         sTab = getNextLine(sTab[1]);
      }
      if (sTab[1].length() != 0) {
         out.write(sTab[1]);
      }
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      this.write(new String(cbuf), off, len);
   }

   @Override
   public void write(int c) throws IOException {
     if (firstLine == true) {
            if (c != '\n') {
               firstLine = false;
                out.write(line++ + "\t"); // this is a counter for current line we are working on
                
            }
        }
        out.write(c);
        if (c == '\n' || c == '\r') { // on next separator, use firstline var to begin a new line
            firstLine = true;
        }

   }

}
