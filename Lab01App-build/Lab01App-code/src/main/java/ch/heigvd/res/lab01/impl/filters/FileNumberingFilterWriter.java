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
 * @modified by Luana Martelli
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int nbLigne = 0;
  private boolean rSeparator = false;
  private boolean nSeparator = false;
  private boolean lineStart = true;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    for (int i = off; i < off + len; ++i) {
      write(str.charAt(i));
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

    /* By default, it's the beginning of the sentence so we add the number of it */
    if (lineStart) {
      out.write(++nbLigne + "\t");
      lineStart = false;
    }

    /* We check for MAC OS line separator \r
    * We need to make the check before writing the character because the \r was the previous character and if the current
    * char is not a \n, then we need first to start a new line*/
    if ((rSeparator && c != '\n')) {
      out.write(++nbLigne + "\t");
    }

    out.write(c);

    /* We check for Linux separator \n
    * We must make this test after we write the \n, otherwise it will be put on the wrong line
    * We have to link the two conditions, otherwise they could both be true, since we update n's boolean to true
    * In this case, we make the test after writing the char because we are focus on the current letter */
    if (c == '\n') {
      nSeparator = true;
      out.write(++nbLigne + "\t");
    }
    /* We check for Windows separator \r\n
    * The boolean is usefull to know if the previous char was a \r*/
     else if (rSeparator && nSeparator) {
      out.write(++nbLigne + "\t");
    }

    /* We update the boolean */
    rSeparator = (c == '\r');
    nSeparator = (c == '\n');

  }

}
