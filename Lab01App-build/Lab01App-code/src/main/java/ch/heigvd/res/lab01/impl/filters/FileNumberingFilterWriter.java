package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
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
 * @author Olivier Liechti, modified by Guillaume Milani
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private int lineCounter = 1;
  private boolean wasR = false;
  private boolean newLine = true;
  private boolean justNumbered = false;
  
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = off; i < off+len; i++) {
        write(cbuf[i]);
    }
  }
  
  private void number() throws IOException {
      if (newLine && !justNumbered) {
        out.write(lineCounter++ + "\t");
        justNumbered = true;
        newLine = false;
    }
  }

  @Override
  public void write(int c) throws IOException {
    number();
    if (c == '\r') {
        wasR = true;
        justNumbered = false;
        out.write(c);
    } else if (c == '\n') {
        wasR = false;
        newLine = true;
        out.write(c);
        number();            
    } else if (wasR) {
        wasR = false;
        newLine = true;
        number();      
        out.write(c);
        justNumbered = false;
    } else {
        out.write(c);
        justNumbered = false;
    }
  }
}
