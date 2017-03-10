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
  private boolean isFirstLine = true;
  char lastChar = '\0';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str) throws IOException {
    final int STR_LENGHT = str.length();

    for (int i = 0; i < STR_LENGHT; ++i) {
      write(str.charAt(i));
    }
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String strOutput = str.substring(off, off + len);

    write(strOutput);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = off; i < off + len; ++i)
      write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {
    char currChar = (char) c;
    StringBuilder output = new StringBuilder();

    if (isFirstLine) {
      output.append(++nbLine).append('\t').append(currChar);
      isFirstLine = false;

    } else if (lastChar == '\r' && currChar == '\n') {
        output.append(lastChar).append(currChar).append(++nbLine).append('\t');

    } else if (lastChar == '\r' && currChar != '\n') {
      output.append(lastChar).append(++nbLine).append('\t').append(currChar);

    } else if (currChar == '\n') {
      output.append(currChar).append(++nbLine).append('\t');

    } else if (currChar != '\r') {
      output.append(currChar);

    }

    lastChar = currChar;

    super.write(output.toString(), 0, output.length());
  }

}
