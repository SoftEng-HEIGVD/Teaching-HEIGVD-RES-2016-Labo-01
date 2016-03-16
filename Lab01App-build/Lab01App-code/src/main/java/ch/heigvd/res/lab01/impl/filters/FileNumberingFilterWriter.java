package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Level;
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
  // for ...
  private boolean firstChar = true;
  // for ...
  private boolean nextChar;
  private int counter = 1;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for (int i = off; i < (off + len); i++) {
      write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
    for (int i = off; i < (off + len); i++) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    // special treatment for the first character
    if (firstChar) {
      out.write(counter + "\t");

      counter++;
      firstChar = false;
    }

    if (c == '\n' && !nextChar) {
      out.write((char)c + String.valueOf(counter) + "\t");

      counter++;
    }
    else {
      if (nextChar) {
        // we have the line separator \r\n
        if (c == '\n') {
          out.write((char)c + String.valueOf(counter) + "\t");
        }
        else {
          out.write(String.valueOf(counter) + "\t" + (char)c);
        }

        counter++;
        nextChar = false;
      }
      else {
        if (c == '\r') {
          // to know if the next character that will be send is a \n
          nextChar = true;
        }

        // we write the character
        out.write(c);
      }
    }
  }
}
