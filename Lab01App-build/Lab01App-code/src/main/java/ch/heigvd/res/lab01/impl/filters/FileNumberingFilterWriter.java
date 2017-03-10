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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int noLine = 1;
  private boolean flagR = true;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = 0; i < len; i++) {
        this.write(cbuf[off + i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
      //automatically adding for the first line
      final int FIRST_LINE=1;
      if(noLine == FIRST_LINE) {
          lineNew();
          flagR = false;
      }
      if(flagR) {
          //if not a newLine found
          if(c != '\n') {
              lineNew();
          }
      }
      flagR = false;
      if(c == '\r') {
          out.write('\r');
          flagR = true;
      } else {
          out.write(c);
          if(c == '\n'){
              lineNew();
          }
     }
  }
  private void lineNew () throws IOException {
      super.out.write(noLine++ + "\t");
  }
}
