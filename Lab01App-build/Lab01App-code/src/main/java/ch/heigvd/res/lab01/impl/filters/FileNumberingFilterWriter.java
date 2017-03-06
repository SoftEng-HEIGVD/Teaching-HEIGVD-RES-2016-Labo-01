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
  private boolean rFound = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     
     for(int i = 0; i < len; ++i)
        write(str.charAt(i+off));
     
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     
     for(int i = 0; i < len; ++i)
        write(cbuf[i+off]);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
     // We are at line #1
     if(lineNumber == 1){
        out.write(lineNumber + "\t");
        lineNumber++;
     }
     // We previously wrote a '\r', we check if it is not followed by a '\n'
     if(rFound == true){
        if((char)c != '\n'){
           out.write(lineNumber + "\t");
           lineNumber++;  
        }
     }
     rFound = false;
     
     if((char)c == '\r'){
        out.write('\r');
        rFound = true;
     } else {
        out.write((char)c);
        if(c == '\n'){
           out.write(lineNumber + "\t");
           lineNumber++;
        } 
     } 
     
        
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  // Before closing, we check if an '\r' is pending, so that we have to add the lineNumber on a blank line
  public void close() throws IOException{
        super.close();
        if(rFound == true){
           out.write(lineNumber + "\t");
        }
     }
}
