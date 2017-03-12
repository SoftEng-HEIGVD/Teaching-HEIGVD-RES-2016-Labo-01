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
  private int lineCounter = 0;
  private boolean readCarriageReturn = false;


  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  private String getNextNumbering() {
    return Integer.toString(++lineCounter) + '\t';
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    for (int i = 0; i < len; i++) {
      write(str.charAt(off + i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = 0; i < len; i++) {
      write(cbuf[off + i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    if (readCarriageReturn) {
      // Windobe EOL
      if ((char) c == '\n') {
        out.write(c);
        out.write(getNextNumbering());
      }
      // MacOS EOL
      else {
        out.write(getNextNumbering());
        out.write(c);
      }

    } else {
      // For the first line of the file
      if (lineCounter == 0) {
        out.write(getNextNumbering());
      }

      out.write(c);

      // Linux EOL
      if ((char) c == '\n') {
        out.write(getNextNumbering());
      }
    }

    readCarriageReturn = (char) c == '\r';
  }
}
