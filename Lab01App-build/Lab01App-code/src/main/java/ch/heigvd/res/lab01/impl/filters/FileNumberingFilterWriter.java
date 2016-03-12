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
  private int lineNum = 0;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    str = transform(str);
    out.write(str, off, str.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    write("" + (char)c, 0, 1);
  }

  private String transform(String s) {

    String[] tmp = Utils.getNextLine(s);
    String res = lineNum == 0 ? (++lineNum) + "\t" + s : s;

    /*
     * After Utils.getNextLine:
     * - tmp[0] == "" -> no line separator in the line passed to getNextLine
     * - tmp[1] == "" -> line passed is the last line and finishes with a line separator
     * So if there is a line separator, we must write next line with the number and a \t
     */
    while (!tmp[0].equals("")) {
      tmp = Utils.getNextLine(tmp[1]);
      res += (++lineNum) + "\t" + tmp[0];

      // The new tmp[1] is "" ->  the new tmp[0] is the last line and finishes with a line separator.
      if (tmp[1].equals("")) {
        break;
      }
    }
    return res;
  }
}
