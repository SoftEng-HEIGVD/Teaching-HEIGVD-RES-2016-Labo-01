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
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
   private boolean charR;
   private int lineCounter;

  public FileNumberingFilterWriter(Writer out){
     super(out);
     charR = false; // Used for \r
     lineCounter = 1; // For first line
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     for(int i = off; i < (off + len); i++){
        this.write(str.charAt(i));
     }
  }
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     for (int i = off; i < (off + len); i++) {
        this.write(cbuf[i]);
     }

  }

  @Override
  public void write(int c) throws IOException {
     // First line
     if (lineCounter == 1){
        out.write(String.valueOf(lineCounter++));
        super.write('\t');
     }
     // End of line : we start a new line and write the number
     if (c == '\n') {
        super.write(c);
        out.write(String.valueOf(lineCounter++));
        super.write('\t');

        // If last character was a '\r', it is a Windows syntax
        if (charR) {
           charR = false;
        }
     } else if(charR){ // MAC OS
        out.write(String.valueOf(lineCounter++));
        super.write('\t');
        super.write(c);
        charR = false;
     } else {
        super.write(c);
     }
     // When we meet a '\r', it can be a MAC OS syntax, we just record it for next character
     if (c == '\r'){
        charR = true;
     }
  }
}
