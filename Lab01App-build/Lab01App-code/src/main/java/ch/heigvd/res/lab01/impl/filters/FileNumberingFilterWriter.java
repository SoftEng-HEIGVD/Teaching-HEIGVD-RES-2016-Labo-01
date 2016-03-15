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
  private int lineNumber = 1;

  /**
   * Used to store if and EOL has been written.
   */
  private boolean eol = true;

  /**
   * Used to store the EOL written (only in the write(int) method).
   */
  private char lastEol;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String[] lines = Utils.getNextLine(str.substring(off, off + len));

    // Write new line number if an EOL has already been written,
    // or if this is the begining of the String.
    if (eol) {
      writeLineNumber();
      eol = false;
    }

    // If the first String is not null, there is an EOL.
    // Write the line and keep in memory : EOL has been written.
    // And do a recursive call to write the rest of the String.
    if (!lines[0].equals("")) {
      super.write(lines[0], 0, lines[0].length());
      eol = true;
      write(lines[1], 0, lines[1].length());
    }

    // If there is not EOL in the String, write all the String
    else {
      super.write(lines[1], 0, lines[1].length());
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {

    // Write new line number if an EOL has already been written,
    // or if this is the begining of the String.
    // Don't write the line number if the EOL is "\r\n".
    // This works with multiple carriage returns (not tested in the unit tests).
    if (eol && !(lastEol == '\r' && c == '\n')) {
      writeLineNumber();
      eol = false;
    }

    // EOL character
    if (c == '\n' || c == '\r') {
      eol = true;
      lastEol = (char) c;
    }

    super.write(c);
  }

  /**
   * Write the current line number and a tabulation character.
   *
   * @throws IOException
   */
  private void writeLineNumber() throws IOException {
    String ln = lineNumber++ + "\t";
    super.write(ln, 0, ln.length());
  }

}
