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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNum = 0;
  private boolean wroteR = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = off; i < cbuf.length && i < off+len; ++i) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    char toWrite = (char)c;

    // Always write line number + tab if beginning
    if (lineNum == 0) {
      wrLN();
    }

    // Always write line number + tab after \n
    if (toWrite == '\n') {
      out.write(c);
      wrLN();
    }

    else if (toWrite == '\r') {
      // here, \r is a new line since \r was written before -> write a new line with its number
      if (wroteR) {
        out.write(c);
        wrLN();
      }
      out.write(c);
      wroteR = true;
    }

    else {
      // if a \r was written before and expecting a n but another char is written, we must put the number + tab
      if (wroteR) {
        wrLN();
      }
      out.write(toWrite);
    }
  }

  /**
   * Writes the current line number + a tab to the stream.
   * @throws IOException
   */
  private void wrLN() throws IOException {
    out.write(String.valueOf(++lineNum) + "\t");
    wroteR = false;
  }
}
