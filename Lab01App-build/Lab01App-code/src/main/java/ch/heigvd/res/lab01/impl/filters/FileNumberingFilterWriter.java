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

  private long nextLine = 1;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      if(!str.isEmpty()){
          StringBuilder sb = new StringBuilder(str);
          String newLine1 = "\n";
          String newLine2 = "\r";
          String newLine3 = "\r\n";
          
          sb.replace(0, 1, nextLine + "\t");
          nextLine++;
          
          int i = 0;
          if((i = sb.indexOf(newLine3)) != -1){
               sb.replace(i, newLine3.length() , nextLine + "\t");
               nextLine++;
               while((i = sb.indexOf(newLine3)) != -1){
                   sb.replace(i, newLine3.length() , nextLine + "\t");
                   nextLine++;
               }
          }
          else if((i = sb.indexOf(newLine2)) != -1){
              sb.replace(i, newLine2.length() , nextLine + "\t");
               nextLine++;
               while((i = sb.indexOf(newLine2)) != -1){
                   sb.replace(i, newLine2.length() , nextLine + "\t");
                   nextLine++;
               }
          }
          else if((i = sb.indexOf(newLine1)) != -1){
              sb.replace(i, newLine1.length() , nextLine + "\t");
               nextLine++;
               while((i = sb.indexOf(newLine1)) != -1){
                   sb.replace(i, newLine1.length() , nextLine + "\t");
                   nextLine++;
               }
          }
          super.write(sb.toString(), off, len);
          
      }
      super.write(str, off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      write(cbuf.toString(), off, len);
  }

  @Override
  public void write(int c) throws IOException {
      super.write(c);
  }

}
