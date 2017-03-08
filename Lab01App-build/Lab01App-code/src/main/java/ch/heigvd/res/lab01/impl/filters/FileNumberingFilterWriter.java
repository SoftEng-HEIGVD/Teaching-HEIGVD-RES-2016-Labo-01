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
 * @author Julien Brêchet
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNumber = 1;
  private boolean r = false; // used to detect \r before \n

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // We need to convert the String in an array of char if we want to use it with an offset and a length
    this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = 0; i < len; ++i)
      this.write(cbuf[off + i]);
  }

  @Override
  public void write(int c) throws IOException {

    // If we are at the first line, we directly write the line number with the tab
    if(lineNumber == 1) {
      out.write(lineNumber + "\t");
      ++lineNumber;
    }
    // If the last character was a '\r', we check if it's followed by a '\n'
    if(r == true) {
      if((char)c != '\n') {
        out.write(lineNumber + "\t");
        ++lineNumber;
      }
    }

    r = false;

    // If we detect a '\r', we write it and we remember we have a '\r' --> r = true
    if((char)c == '\r') {
      out.write('\r');
      r = true;
    } else {
      out.write((char)c);
      if(c == '\n'){
        out.write(lineNumber + "\t");
        ++lineNumber;
      }
    }
  }
}