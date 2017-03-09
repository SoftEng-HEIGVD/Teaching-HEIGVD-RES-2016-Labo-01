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
  private boolean firstCHar = true;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str) throws IOException {
    String strOutput;
    String out[];

    if (nbLine == 0)
      strOutput = ++nbLine + "\t";
    else
      strOutput = "";

    out = Utils.getNextLine(str);

    while (!out[0].isEmpty()) {
      if(!out[0].isEmpty()) {
        strOutput += out[0] + ++nbLine + "\t";
      } else {
        strOutput += out[1] + "\n" + ++nbLine + "\t";
      }

      out = Utils.getNextLine(out[1]);
    }

    strOutput += out[1];

    super.write(strOutput, 0, strOutput.length());
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
    if (c != '\r')
      write(Character.toString((char) c));
    else
      super.write(c);

  }

}
