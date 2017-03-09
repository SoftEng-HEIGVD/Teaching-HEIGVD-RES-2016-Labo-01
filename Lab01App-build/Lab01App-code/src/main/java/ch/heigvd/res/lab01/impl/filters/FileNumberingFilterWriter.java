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

  private int lineNbr = 1;
  private boolean firstChar = true;
  private int previous;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++)
       write(str.charAt(i));
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     for(int i = off; i < off + len; i++)
        write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {
     if(firstChar){
        newLine();
        firstChar = false;
     }
     
     if(c == '\n'){
        super.write(c);
        newLine();       
     }
     else if(previous == '\r'){
        newLine();
        super.write(c);
     }
     else
        super.write(c);
     
     previous = c;
  }
  
  private void newLine() throws IOException{
     super.out.write(lineNbr++ + "\t");
  }

}
