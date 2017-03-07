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

  private int lineCount = 1;
  private STATE state = STATE.NEW_LINE;

  public enum STATE{
      NEW_LINE,
      END_LINE,
      R_READ,
      STANDARD
  }

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
        for(int i=off;i<off+len;i++)
            write(str.charAt(i));
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      for(int i=off;i<off+len;i++)
          write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {

      //Checking if previous \r was single separator
      if(state.equals(STATE.R_READ)&&c!='\n'){
            state = STATE.NEW_LINE;
          out.write(lineCount++ + "\t");
      }

      //If new line
      if (state.equals(STATE.NEW_LINE)) {
          if(lineCount==1)
              out.write(lineCount++ + "\t");
          state = STATE.STANDARD;
      }

      out.write(c);

      //Check if \r was previously read and \n just written -> \r\n separator
      //or \n single separator
      if(c=='\n') {
          state = STATE.NEW_LINE;
          out.write(lineCount++ + "\t");
      }
      else if(c=='\r')
          state = STATE.R_READ;
  }

}
