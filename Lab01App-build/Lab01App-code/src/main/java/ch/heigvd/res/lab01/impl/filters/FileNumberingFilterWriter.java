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
  
  private int lineNumber = 1;
  private char previous;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    int length = off + len;
    
    if(off < 0 || len < 0 || 
       length < 0 || length > cbuf.length) {
      
      throw new IndexOutOfBoundsException();
    }
    
    for(int i = off; i < off + len; i++) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    
    // For the first line
    if(previous == '\u0000') {
      header();
    }
    
    // For MacOS9
    if(previous == '\r' && c != '\n') {
      header();
    }
    
    super.write(c);

    if (c == '\n') {
      header();
    }
    
    previous = (char)c;
  }
  
  private void header() throws IOException {
      super.out.write(Integer.toString(lineNumber++));
      super.write('\t');
  }

}
