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
  private int line;
  private boolean windowsNewlineTest;
  public FileNumberingFilterWriter(Writer out) {
    super(out);
    line = 1;
    windowsNewlineTest = false;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      for (int i = 0; i < len; i++) {
           write((int)(cbuf[off+i]));
      }
  }

  /**
   * We have to be careful to distinguish \r from \r\n line seperators.
   * To do this, a \r will always be considered a newline, but will set a 
   * flag to true so that, if a \n is detected as the next char, it will 
   * be written without treating it as a second new line.
   * 
   * 
   */
  @Override
  public void write(int c) throws IOException {
      if(line == 1){
          out.write(line + "\t");
          line++;
      }
      if(windowsNewlineTest){  //the last char seen was a \r
          if (c != '\n'){  
            out.write(line + "\t");
            line++;
            windowsNewlineTest = false;
          }else{
              out.write((char)c);
              out.write(line + "\t");
              line++;
              windowsNewlineTest = false;
              return;
          }
      }
      out.write((char)c);
      if(c == '\n'){
            out.write(line + "\t");
            line++;
      }else if(c == '\r'){
            windowsNewlineTest = true;
      }
  }

}
