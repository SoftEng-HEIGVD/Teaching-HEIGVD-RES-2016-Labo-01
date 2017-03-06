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
  private int car;
  private int  lineNumber = 0;
  
  
  
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      // Calls the write with char[]
      write(str.toCharArray(), off, len); 
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      // For each character it calls the write with integer
      for (int i = 0; i < len && (off + i) < cbuf.length; ++i)
         write(cbuf[off + i]);
      
  }

  @Override
  public void write(int c) throws IOException {
   
      // Checks if it is the first line
   if (lineNumber == 0) {
        super.write('1');
        super.write('\t');
        ++lineNumber;
      }
      // Checks if the character is the line return
      if (c == '\n') {
          super.write(c);
          char tmp = (String.valueOf(++lineNumber)).charAt(0);
          super.write(tmp);
          super.write('\t');
      } 
      
      // Checks if the character is \r
      else if (car =='\r') {
          char tmp = (String.valueOf(++lineNumber)).charAt(0);
          super.write(tmp);
          super.write('\t');
          super.write(c); 
      } 
      
      // Otherwise it writes the character directly
      else
          super.write(c);
            
      car = c;
  }
 }

  
