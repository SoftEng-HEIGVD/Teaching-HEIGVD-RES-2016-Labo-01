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
 *  @author Adrien Marco
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNumber = 1; 
  private boolean foundR = false;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     //we calle the fucntion with char[] parameter
     write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //we call the write function for each character
     for(int i = 0; i < len; ++i){
        write(cbuf[off+i]);
     }
  }

  //build the header of a line with the number and the tab
  public void writeHeaderLine()throws IOException {
     out.write(lineNumber+"\t");
     lineNumber++;
  }
  
 @Override
  public void write(int c) throws IOException {
     
    //when we are on the fisrt line
    if(lineNumber == 1){
       writeHeaderLine();
    }
    //if we found a return then we can now to write the header line
    if(foundR == true){
       //but the character must be different as return character '\n'
       if((char)c != '\n'){
          writeHeaderLine();
       }
    }
    
    //if no return characters found
    foundR = false;

    out.write((char)c);
    
    //if we found now a return character, write the header or remember it
    if((char)c == '\r'){
     foundR = true;
    }
    else if (c == '\n'){
         writeHeaderLine();
    }
  }
}
