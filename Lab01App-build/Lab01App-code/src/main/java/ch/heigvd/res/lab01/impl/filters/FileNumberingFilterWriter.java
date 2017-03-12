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
  private int nbLine = 1;
  private boolean newLine = true;
  private int lastChar = 0;
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     for(int i = off; i < off + len; i++)
     {
        this.write(str.charAt(i));
     }
     //System.out.println(formattedString);
    
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < off + len; i++)
     {
        this.write(cbuf[i]);
     }
  }

  @Override
  public void write(int c) throws IOException {
      //if it's the first time we call this method we write a new line
     if(newLine)
     {
        out.write(nbLine + "\t");
        nbLine++;
        newLine = false;
     }
     
     //if the caractere of endline is '\r' we wait next call to see if there is a '\n' to write befor nextLine
     if(lastChar == '\r' && c != '\n')
     {
        out.write(nbLine + "\t");
        nbLine++;
     }
     
     out.write(c);
     
     //if it's a '\n' we are sure that it's a new line
     if(c == '\n')
     {
        out.write(nbLine + "\t");
        nbLine++;
     }
     //keep in memory the last caractere for next calls of the method
     lastChar = c;
     
  }

}
