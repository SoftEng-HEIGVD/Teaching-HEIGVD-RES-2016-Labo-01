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
  private boolean wroteNewLine;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    str = transform(str.substring(off, off+len));
    out.write(str, 0, str.length());
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

    /*
     * See getNextLine for detailed behaviour. The important facts in this function are :
     * - tmp[0] == "" -> no line separator in the line passed to getNextLine.
     * - tmp[1] == "" -> line passed is the last line and finishes with a line separator.
     */
    String[] tmp = Utils.getNextLine(s);

    // If it's the first line, we add the line number and a tab.
    String res = lineNum == 0 ? (++lineNum) + "\t" + tmp[0] : tmp[0];

    // We can return res here if s does not contain any line separator.
    if (tmp[0].equals("")) {
      res += tmp[1];
      return res;
    }

    // While there are still a line to transform -> line number + tab char + line
    while (!tmp[0].equals("")) {
      tmp = Utils.getNextLine(tmp[1]);
      // We write the line. If it's the last, we must take that into account too and write tmp[1] instead
      res += (++lineNum) + "\t" + (tmp[0].equals("") ? tmp[1] : tmp[0]);

      // The new tmp[1] is "" ->  the new tmp[0] is the last line and finishes with a line separator.
      // Since the result of getNextLine will be the same next iteration, we can quit here.
      if (tmp[1].equals("")) {
        break;
      }
    }

    return res;
  }
}
