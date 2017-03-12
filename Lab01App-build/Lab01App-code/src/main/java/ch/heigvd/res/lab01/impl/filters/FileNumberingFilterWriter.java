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
 * @author Iando Rafidimalala
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
  private Boolean isPreviousMacEOL = false;
  private int noLine = 1;
  
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }


  @Override
  public void write(String str, int off, int len) throws IOException {
      this.write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      for(int i = off ; i < off + len ; i++)
          this.write(cbuf[i]);
  }
  
  
  @Override
  public void write(int c) throws IOException {
/**
 * Be ware to write the header when the process encounter the carriage return or the first char
 */      
    if (noLine == 1 || (isPreviousMacEOL && c != '\n')){
      out.write(stringHeader());
     }
    
   out.write(c);

   if (c == '\n') {
      out.write(stringHeader());
   }

   isPreviousMacEOL =  c == '\r';
  }
  
  /**
   * 
   * Design the header with the number of line
   */
  private String stringHeader(){
      String tmp = noLine + "\t";
      
      noLine++;
      
      return tmp;
  }
}
