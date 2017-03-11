package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

import ch.heigvd.res.lab01.impl.Utils; 

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * modified by Abass MAHDAVI
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNumber = 1;
  private boolean endOfLine = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException { 
    for (int pos = off; pos < off + len ; pos++){
        write(str.charAt(pos));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int pos = off; pos < off + len ; pos++){
        write(cbuf[pos]);
    }
  }

  @Override
  public void write(int c) throws IOException {      
      String result = "";
      if (lineNumber == 1)
      {
          result = lineNumber++ + "\t";
      } 
      if ((char)c == '\n')
      {
          result += "\n" + lineNumber++ + "\t" ;  
          endOfLine = false;
      }
      else if ((char)c == '\r')
      {
          result += (char)c; 
          endOfLine = true;
      }
      else 
      {
          if (endOfLine == true)
          {
              result += lineNumber++ + "\t" ;  
              endOfLine = false;
          }
          result += (char)c; 
      }
      
      out.write(result);

  }
}
