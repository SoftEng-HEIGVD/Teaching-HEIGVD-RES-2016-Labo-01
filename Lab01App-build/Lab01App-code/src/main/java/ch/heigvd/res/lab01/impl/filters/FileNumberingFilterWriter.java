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
 * @author Dany Simo
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
// declaration and initialisation of line's number and boolean if a '\r' is found
  private int lineNumber = 1;
  private boolean returnFound = false;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      // conversion string to char[]
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
        for(int i = off; i < off + len; i++) 
           write(cbuf[i]);
    
  }

  @Override
  public void write(int c) throws IOException {
     // at line 1
     if(lineNumber == 1){
         // writes the line's number and increment it
        out.write(lineNumber + "\t");
        lineNumber++;
     }
     //next time
     //check if '\n' isn't followed by '\r'
     if(returnFound == true){
        if((char)c != '\n'){
            // if exist write the line number
           out.write(lineNumber + "\t");
           lineNumber++;  
        }
     }
     returnFound = false;
     //first time
     //check if c is a '\r' or not then write it
     if((char)c == '\r'){
        out.write('\r');
        returnFound = true;
     } else {
        out.write((char)c);
        if(c == '\n'){
           out.write(lineNumber + "\t");
           lineNumber++;
        } 
     }
  }
}
