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
 * @author Luca Sivillica
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int nbLine = 0;
  private boolean isFirstLine = true;
  char lastChar = '\0'; // Last character read

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str) throws IOException {

    final int STR_LENGTH = str.length();

    for (int i = 0; i < STR_LENGTH; ++i) {
      write(str.charAt(i));
    }
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String strOutput = str.substring(off, off + len);

    write(strOutput);
  }

  @Override
  public void write(char[] cbuf) throws IOException {

    for (int i = 0; i < cbuf.length; ++i) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    for (int i = off; i < off + len; ++i) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    char currChar = (char) c; // Current character read
    StringBuilder output = new StringBuilder();


    /* If we are reading the first line, we need to write the line number
       and the tabulation character before to write the first character */
    if (isFirstLine) {
      output.append(++nbLine).append('\t').append(currChar);
      isFirstLine = false;


      /* We check if the end line is \r\n and after it we write the line number
         and the tabulation character */
    } else if (lastChar == '\r' && currChar == '\n') {
        output.append(lastChar).append(currChar).append(++nbLine).append('\t');


      /* We check if the end line is \r and after it we write the line number
         and the tabulation character */
    } else if (lastChar == '\r' && currChar != '\n') {
      output.append(lastChar).append(++nbLine).append('\t').append(currChar);


      /* We check if the end line is \n and after it we write the line number
         and the tabulation character */
    } else if (currChar == '\n') {
      output.append(currChar).append(++nbLine).append('\t');


      /* We check if we are into the line in order to write normally
         the current character */
    } else if (currChar != '\r') {
      output.append(currChar);

    }

    lastChar = currChar; // We need to memorise the last character in order to detect "\r\n"

    super.write(output.toString(), 0, output.length());
  }

}
