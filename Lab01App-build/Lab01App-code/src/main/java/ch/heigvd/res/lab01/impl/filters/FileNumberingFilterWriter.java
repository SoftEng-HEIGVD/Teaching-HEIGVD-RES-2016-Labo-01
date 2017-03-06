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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private char previousChar; //Use previous character for \r\n in Windows
  private int numberLine = '0';  //Use for the line number

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      /*Call the function write with char[] in param*/
      write(str.toCharArray(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      /*for each character from offset(off) and lenght(len) in cbuf,
      we call the function write which manage one character*/
      for(int i = off ; i < off + len ; i ++) 
      {
          write((int)cbuf[i]);
      }
  }

  @Override
  /*Character for new line in MAC \r LINUX \n and WINDOWS \r\n*/
  public void write(int c) throws IOException {
             
      /*Test if it is the first character('\u0000') or if it is a MAC os*/
      if( previousChar == '\r' && (char)c != '\n' || previousChar == '\u0000')
      {          
          writeHeadLine();
      }
      
      super.write(c);
      
      /*Test if it is the last character in the line*/
      if(c == '\n')
      {
          writeHeadLine();
      }
      
      previousChar = (char) c;
  }
  
  /*Write the line number and tabulation before the line*/
  private void writeHeadLine() throws IOException
  {      
      super.write(++numberLine);
      /*Tab*/
      super.write('\t');
  }

}
