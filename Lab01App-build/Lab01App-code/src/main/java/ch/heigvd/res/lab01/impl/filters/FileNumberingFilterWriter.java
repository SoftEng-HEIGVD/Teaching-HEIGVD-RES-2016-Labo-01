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
  private int counter = 1;
  private boolean eol = true;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  /**
   * writes a string to the outputstream.
   *
   * For the sake of simplicity and DRY principle, we just convert this
   * to an array and call the write with a bytearray
   *
   * @param str: the string to write
   * @param off: offset at which to start writing
   * @param len: length to write
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  /**
   * Writes a bytearray to the outputstream.
   * This function internally calls write on a byte by byte way,
   * It should not impact performance seriously if you use
   * a buffered outputstream, which is recommended
   *
   * @param cbuf: buffer to write
   * @param off: offset at which to start writing
   * @param len: length to write
   * @throws IOException
     */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i=off; i < (len + off); i++) {
      write(cbuf[i]);
    }
  }

  /**
   * Writes the number of the line and a tab
   *
   * @throws IOException
     */
  private void write_line_header() throws IOException {
    eol = false;

    for(char x: Integer.toString(counter).toCharArray()) {
      super.write(x);
    }
    counter++;
    super.write('\t');
  }

  /**
   * writes the int (byte) given in parameter to the stream.
   * If this is a new line, also adds the line header afterwards
   *
   * @param c: value to write
   * @throws IOException
     */
  @Override
  public void write(int c) throws IOException {
    if(eol && ('\n' != c)) {
      write_line_header();
    }

    super.write(c);

    if('\r' == c) {
      eol = true;
    }
    else if('\n' == c) {
      write_line_header();
    }
  }

}
