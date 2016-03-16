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
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private char prev;
  private int lineNum = '0';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off+len; i++)
    {
        write((int)cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
      //begining of file
      if(prev == '\u0000')
      {
          lineNumbering();
      }
      
      //for Mac
      if(prev == '\r' && (char)c != '\n')
      {
          lineNumbering();
      }
      // Write the current character
      super.write((char)c);
      
      if(c == '\n')
      {
          lineNumbering();
      }
           
      prev = (char)c;
  }

  /* print the line number and a tab character*/
  private void lineNumbering() throws IOException 
  {
      super.write(++lineNum);
      super.write('\t');
  }
}
