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
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int nbLine = 0;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String strTransformed = str.substring(off, off + len);
    String[] tmp;

    tmp = Utils.getNextLine(strTransformed);

    if (nbLine == 0)
      strTransformed = ++nbLine + "\t";

    while (!tmp[0].isEmpty()) {

      strTransformed += tmp[0] + ++nbLine + "\t";
      tmp = Utils.getNextLine(tmp[1]);
    }

    super.write(strTransformed, 0, strTransformed.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    super.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    int nbLine = 1;

    super.write(nbLine++ + "\t" + c);
  }

}
