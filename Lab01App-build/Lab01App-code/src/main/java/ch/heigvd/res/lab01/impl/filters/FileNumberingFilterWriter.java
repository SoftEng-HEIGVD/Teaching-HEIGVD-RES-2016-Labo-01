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
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Loan Lassalle
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private boolean isCarriageReturn;
  private int lineNumber;

   /**
   * Write line number and a tabulation in the writer.
   *
   * @throws   IOException
   */
  private void lineNumbering() throws IOException {
    out.write(String.valueOf(++lineNumber) + '\t');
  }

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    final int maxLength = off + len;
    
    for(int i = off; i < maxLength; ++i)
    {
        write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // A line number should be write in the writer before the character c
    // if it's first line or if the last character was '\r' and the current
    // character c is not '\n'
    if(lineNumber == 0 || isCarriageReturn && c != '\n')
    {
        lineNumbering();
    }
    
    out.write(c);
    isCarriageReturn = c == '\r';
    
    // A line number should be writer after the character c
    // if this character was '\n'
    if(c == '\n')
    {
        lineNumbering();
    }
  }

}
