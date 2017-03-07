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
  private int lineNumber = 1;
  // Check if has \r character
  private boolean hasSlashR = true;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // String to array and call the adequate method
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // For every character in string, call method write
    for(int i = off; i < (off + len); i++){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(hasSlashR && c != '\n'){
      writeHeadLine();
    }
    // if has \r before, the next character will not be \r
    hasSlashR = false;

    // write character
    out.write(c);

    if(c == '\n'){
      writeHeadLine();
    }
    else if(c == '\r'){
      hasSlashR = true;
    }
  }

  /**
   * This method write the heads characters of a line (the line number, then a tab character)
   */
  private void writeHeadLine() throws IOException {
    // use variable then increment it
    out.write(String.valueOf(lineNumber++));
    out.write('\t');
  }

}
