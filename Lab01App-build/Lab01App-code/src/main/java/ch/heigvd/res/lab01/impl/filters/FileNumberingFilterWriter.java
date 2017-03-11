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
  private int count = 1;
  private boolean prevIsCR = true; // Previous character is carriage return

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++) {
      /* Must be very careful when encountering a carriage return. When encountering '\r' we
       * must know whether a line feed ('\n') follows it or not.
       */
      if(cbuf[i] == '\r') {
        if(prevIsCR) {
          writeNewLineNumber();
        }

        out.write(cbuf[i]);
        prevIsCR = true;
      }
      else if(cbuf[i] == '\n') {
        out.write(cbuf[i]);
        writeNewLineNumber();

        prevIsCR = false;
      }
      else {
        if(prevIsCR) {
          writeNewLineNumber();
        }

        out.write(cbuf[i]);
        prevIsCR = false;
      }
    }
  }

  @Override
  public void write(int c) throws IOException {
    write(new char[] {(char)c}, 0, 1);
  }

  /**
   * Writes new line number appended with tab space.
   * Auto-increments state of line number at each call.
   *
   * @throws IOException
   */
  private void writeNewLineNumber() throws IOException {
    out.write((count++) + "\t");
  }
}
