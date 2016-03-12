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
  private boolean wroteNewLine = false;

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

    if (lineNum == 0) {
      write(++lineNum + "\t");
    }

    if (toWrite == '\n' || toWrite == '\r') {
      // ignore new line
      if (wroteNewLine) {
        return;
      }
      super.write(c);
      write(++lineNum + "\t");
      wroteNewLine = true;
    }

    else {
      super.write(toWrite);
      wroteNewLine = false;
    }
  }

  @Override
  public void write(String str) throws IOException {
    write(str, 0, str.length());
  }
}
