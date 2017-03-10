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

  private int lineNo = 1;
  private boolean readBackslashR = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    if (lineNo == 1) {
      String prefix = "" + lineNo++ + "\t";
      super.write(prefix, 0, prefix.length());
    }
    for (int i = off; i < off+len; i++) {
      if(cbuf[i] == '\r') {
        readBackslashR = true;
      }
      else if (cbuf[i] == '\n') {
        if(readBackslashR)
          super.write('\r');
        super.write('\n');
        String prefix = "" + lineNo++ + "\t";
        super.write(prefix, 0, prefix.length());
      }
      else {
        if(readBackslashR) {
          super.write('\r');
          String prefix = "" + lineNo++ + "\t";
          super.write(prefix, 0, prefix.length());
        }
        super.write(cbuf[i]);
      }
      if(cbuf[i] != '\r')
        readBackslashR = false;
    }
  }

  @Override
  public void write(int c) throws IOException {
    char[] b = {(char) c};
    write(b, 0, 1);
  }

}
