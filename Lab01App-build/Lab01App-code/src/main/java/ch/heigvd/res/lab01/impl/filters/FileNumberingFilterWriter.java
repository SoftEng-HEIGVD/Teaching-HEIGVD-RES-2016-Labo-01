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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private static boolean flagR = false;
  private static boolean flagN = false;
  private boolean first = true;

  private int line = 1;


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
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if (first || (flagR && c != '\n')) { // first time OR MacOSX
      lineHead();
      first = false;
    }

    out.write(c);

    if (c == '\n') { // Linux
      lineHead();
      flagN = true;
    } else if (flagR && flagN) { // Windows
      lineHead();
    }

    //Update flags
    flagR = (c == '\r');
    flagN = (c == '\n');
  }

  private void lineHead() throws IOException {
    out.write(String.valueOf(line++));
    out.write('\t');
  }
}
