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
 * @author Christopher Meier
 */
public class FileNumberingFilterWriter extends FilterWriter {
  private boolean hadNewline = true;
  private boolean extendedNewLine = false;
  private int lineCount = 1;

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    if(lineCount == 1)
      decorateLineNumber();

    String sub = str.substring(off, off + len);
    String[] sep = Utils.getNextLine(sub);

    while (!sep[0].equals("")) {
      super.write(sep[0], 0, sep[0].length());
      decorateLineNumber();
      sep = Utils.getNextLine(sep[1]);
    }

    super.write(sep[1], 0, sep[1].length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    if(extendedNewLine && c != '\n')
      extendedNewLine = false;

    if(hadNewline && !extendedNewLine)
      decorateLineNumber();

    super.write(c);

    if(c == '\r') {
      hadNewline = true;
      extendedNewLine = true;
    } else if(c == '\n') {
      hadNewline = true;
      extendedNewLine = false;
    } else {
      hadNewline = false;
      extendedNewLine = false;
    }
  }

  private void decorateLineNumber() throws IOException{
    String lineHeader = String.format("%d\t", lineCount);
    lineCount++;
    super.write(lineHeader);
  }

}
