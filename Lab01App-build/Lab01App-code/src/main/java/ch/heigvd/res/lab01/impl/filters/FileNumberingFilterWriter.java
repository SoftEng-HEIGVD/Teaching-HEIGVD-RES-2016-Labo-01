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
 * @author Olivier Liechti
 * @author Ludovic Richard
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private static int lastChar = 0, nLine = 1;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //String[] line =  Utils.getNextLine(str);

    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < cbuf.length && i < len + off; ++i) {
      this.write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(nLine == 1){
      super.write(Integer.toString(nLine++));
      super.write('\t');
    }

    if(lastChar == '\r' && c == '\n');
      //super.write(c);
    else if(c == '\r' || c == '\n') {
      super.write(c);
      super.write(Integer.toString(nLine++));
      super.write('\t');
    }
    else
      super.write(c);

    lastChar = c;
  }
}
