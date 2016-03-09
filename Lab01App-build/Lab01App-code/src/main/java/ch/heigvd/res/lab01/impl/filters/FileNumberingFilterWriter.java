package ch.heigvd.res.lab01.impl.filters;

import com.sun.xml.internal.ws.api.WSService;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import static ch.heigvd.res.lab01.impl.Utils.getNextLine;
import static java.lang.Integer.min;

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
  private int counter = 1;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String[] nextLine = getNextLine(str);
    while(0 != nextLine[0].length()) {
      try {
        super.write(nextLine[1], off, len);
      }
      catch(IndexOutOfBoundsException ignored) {}

      len -= nextLine[0].length();
      off = min(nextLine[0].length(), 0);

      if(len < 0) {
        return;
      }

      super.write(counter);
      super.write('\t');
      nextLine = getNextLine(nextLine[1]);
    }

    if(0 != nextLine[1].length()) {
      super.write(nextLine[1], off, len);
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    super.write(cbuf, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    super.write(c);
  }

}
