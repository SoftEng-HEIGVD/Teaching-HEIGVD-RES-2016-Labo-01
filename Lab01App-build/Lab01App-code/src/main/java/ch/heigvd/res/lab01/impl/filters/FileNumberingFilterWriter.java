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
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  private int lineNo = 1;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
    /*
    String sub = str.substring(off, len);
    String splits[] = sub.split("\\r?\\n");
    for(int i=0; i<splits.length; i++) {
      String prefix = (lineNo == 1 ? "" : "\n");
      prefix += "" + lineNo++ + "\t";
      super.write(prefix, 0, prefix.length());
      super.write(splits[i], 0, splits[i].length());
    }*/
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    /*String s = new String(cbuf);
    write(s, off, len);*/
    if (lineNo == 1) {
      String prefix = "" + lineNo++ + "\t";
      super.write(prefix, 0, prefix.length());
    }
    for (int i = off; i < len; i++) {
      // \r unix
      super.write(cbuf[i]);
      if (cbuf[i] == '\n') {
        String prefix = "" + lineNo++ + "\t";
        super.write(prefix, 0, prefix.length());
      }
    }
  }

  @Override
  public void write(int c) throws IOException {
    super.write(c);
  }

}
