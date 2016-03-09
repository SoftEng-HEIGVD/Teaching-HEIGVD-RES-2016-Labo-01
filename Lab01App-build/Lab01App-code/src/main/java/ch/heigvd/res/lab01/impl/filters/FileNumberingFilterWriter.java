package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import ch.heigvd.res.lab01.impl.Utils;
import java.lang.StringBuilder;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\nWorld -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
  // attributes
  private int counter = 1;
  private int last = 0; // last character written
  private StringBuilder buffer = new StringBuilder();

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    buffer.append(str.substring(off, off + len));
    doEffectiveWrite();
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    buffer.append(cbuf, off, len);
    doEffectiveWrite();
  }

  @Override
  public void write(int c) throws IOException {
    buffer.append(c);
    doEffectiveWrite();
  }

  protected void doEffectiveWrite() throws IOException {
    if(last != 0)
      buffer.insert(0, last);
    String buf = buffer.toString();
    last = buf.charAt(buf.length() - 1);

    String[] split = Utils.getNextLine(buf);
    if(!split[0].isEmpty()) {
      String numbered = (counter++) + "\t" + split[0];
      super.write(numbered, 0, numbered.length());
      split = Utils.getNextLine(split[1]);
    }

    String numbered = (counter++) + "\t" + split[1];
    super.write(numbered, 0, numbered.length());
  }

}
