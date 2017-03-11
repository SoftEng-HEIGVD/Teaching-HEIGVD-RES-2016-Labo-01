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
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    int newLen = off + len;
    for (int i = off; i<newLen; i++ ){
        write(cbuf[i]);
    }
  }

  private int numberOfLine = 1;
  private int character;
  
  
  @Override
  public void write(int c) throws IOException {
      if (numberOfLine ==1){
      writeHeaderLine();
  }
      
  if ((this.character == '\r' && c != '\n')) {
      writeHeaderLine();
     }
     out.write(c);
     if (c == '\n') {
        writeHeaderLine();
     }
     this.character = c;
  }
  
    private void writeHeaderLine() throws IOException {
        out.write(numberOfLine + "\t");
        numberOfLine++;
    }
}

