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

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
  private int number;
  private boolean firstCall;
  private int lastPrintedChar;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    number = 1; // Remembering the line number
    firstCall = true; // Used for the call with the int c
    lastPrintedChar = 'a'; // Can be anything but \n or \r
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      String toWrite = str;
      
      // Taking the substring if necessary
      if (len > 0)
      {
          toWrite = toWrite.substring(off, off + len);
      }
      String[] partsOfStr = {"", ""};
 
      // Getting the next line
      partsOfStr = Utils.getNextLine(toWrite);
      
      // Adding the first line number
      if(firstCall) {
          firstCall = false;
          out.write(number + "\t");
          number++;
      }
      
      while( ! partsOfStr[0].isEmpty()) {
          out.write(partsOfStr[0] + number + "\t");
          number++;
          partsOfStr = Utils.getNextLine(partsOfStr[1]);
      }
      if ( !partsOfStr[1].isEmpty()) {
          out.write(partsOfStr[1] /*+ number + "\t"*/);
      } 
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String toWrite = new String(cbuf);
    
      // Taking the substring if necessary
      if (len > 0)
      {
          toWrite = toWrite.substring(off, off + len);
      }
      write(toWrite);
  }

  @Override
  public void write(int c) throws IOException {
    
    if (firstCall || lastPrintedChar == '\n' ||
          (lastPrintedChar == '\r' && c != '\n')  ) {
        firstCall = false;
        out.write( number + "\t");
        number++;
    }
   
    out.write(c);
    lastPrintedChar = c;
     
  }
}
