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
 * Hello\nWorld -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Ludovic Delafontaine
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
  private int lineNumber = 1;
  private int lastCharacter = '\u0000';
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    
    // Write characters by characters of the range to the output
    for (int i = off; i < off + len; ++i) {
        write(str.charAt(i));
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    
    // Write characters by characters of the range to the output
    for (int i = off; i < off + len; ++i) {
        write(cbuf[i]);
    }
  }
  
  /**
   * This method add a new line number to the output followed by a tab character
   *
   * @author Ludovic Delafontaine
   */
  private void newLine() throws IOException {
    out.write(String.format("%d\t", lineNumber));
    lineNumber++;
  }

  @Override
  public void write(int c) throws IOException {
      
      // Write new line on first line
      if (lastCharacter == '\u0000') {
        newLine();
      }
      
      // As a line is always 'a new line\n' or 'a new line\r\n', we assume that
      // we always write a new line after the '\n'
      if (c == '\n') {
          out.write(c);
          newLine();
      
      // In case we have the '\r', we must be sure that it's not followed by a
      // '\n'. So if the current character isn't a '\n' but the lastCharacter is
      // a '\r', we must add the new line and then write the character
      } else if (lastCharacter == '\r') {
          newLine();
          out.write(c);
          
      // If the current character is neither a '\n' nor a '\r', then write it
      // to the output
      } else {
          out.write(c);
      }
      
      // Save the current character 
      lastCharacter = c;
      
  }

}
