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
 * Hello\nWorld -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 * @modifiedBy Sebastien Boson
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  // to know if it is the first character to be written or not
  private boolean firstChar = true;
  // to know that a \r could be followed by a \n
  private boolean nextChar;
  // to number the lines
  private int counter = 1;

  /**
   * Constructor for FileNumberingFilterWriter class.
   */
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  /**
   * This method write the specified String that start at the index off and have a length of len in
   * the underlying character-output stream.
   * It does a special treatment. When we are at the first char or before a line separator (\r, \n, \r\n),
   * we put a line number and a \t.
   *
   * @param str the String that will be written
   * @param off the start index of the String
   * @param len the length of what would be written from the String
   * @throws IOException
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    // we call the method that do that char by char
    for (int i = off; i < (off + len); i++) {
      write(str.charAt(i));
    }
  }

  /**
   * This method write the specified array of char that start at the index off and have a length of len in
   * the underlying character-output stream.
   * It does a special treatment. When we are at the first char or before a line separator (\r, \n, \r\n),
   * we put a line number and a \t.
   *
   * @param cbuf the array of char that will be written
   * @param off the start index of the array of char
   * @param len the length of what would be written from the array of char
   * @throws IOException
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // we call the method that do that char by char
    for (int i = off; i < (off + len); i++) {
      write(cbuf[i]);
    }
  }

  /**
   * This method write the specified int in the underlying character-output stream.
   * It does a special treatment. When we are at the first char or before a line separator (\r, \n, \r\n),
   * we put a line number and a \t.
   *
   * @param c int to be written
   * @throws IOException
   */
  @Override
  public void write(int c) throws IOException {
    // special treatment for the first character
    if (firstChar) {
      out.write(counter + "\t");

      counter++;
      firstChar = false;
    }

    // if we have not the line separator \n after a \r
    if (c == '\n' && !nextChar) {
      out.write((char)c + String.valueOf(counter) + "\t");

      counter++;
    }
    else {
      // if we have a \r before
      if (nextChar) {
        // if we have the line separator \r\n
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
          // for the next character, we would remember that a \r is placed just before it
          nextChar = true;
        }

        // we write the character
        out.write((char)c);
      }
    }
  }
}
