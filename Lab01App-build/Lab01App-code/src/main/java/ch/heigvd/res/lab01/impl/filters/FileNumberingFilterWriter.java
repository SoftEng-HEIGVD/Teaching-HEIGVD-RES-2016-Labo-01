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
  private char counter;
  private boolean r_found;
  public FileNumberingFilterWriter(Writer out) {
    super(out);
    counter = '0';
    r_found = false;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet
     str = str.substring(off, off+len);
     String output = "";
     if(!str.equals("")){
         if(counter == '0'){
            counter++;
            output = counter+"\t";
         }
         int start = 0;
         int index_n = str.indexOf("\n");
         int index_r = str.indexOf("\r");
         if(index_n == -1 && index_r == -1){
            output += str;
            out.write(output);
            return;
         }
         while(index_n != -1 || index_r != -1){
           counter++;
           int index = Math.max(index_n, index_r);
           output += str.substring(start, index+1)+counter+"\t";
           start = index+1;
           index_n = str.indexOf("\n", index_n+1);
           index_r = str.indexOf("\r", index_r+1);
         }
         output += str.substring(start, str.length());
      out.write(output);
     }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
     write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
     if(counter == '0'){
        counter++;
        
        out.write(counter);
        out.write("\t");
        out.write(c);
        return;
     }
     if(r_found && c != '\n'){
        counter++;
        out.write(counter);
        out.write("\t");
        r_found = false;
     }
     out.write(c);
     if(c == '\n'){
        counter++;
        out.write(counter);
        out.write("\t");
        r_found = false;
        return;
     }
     if(c == '\r'){
        r_found = true;
        return;
     }
     
  }

}
