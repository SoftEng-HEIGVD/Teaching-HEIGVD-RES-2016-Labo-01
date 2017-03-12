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
 * @author Olivier Liechti, Basile Châtillon
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int nbLine = 0;
  private int previousC = 0;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // we convert the string in an array of char
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // we iterate on the array to apply on each char the write(int ) methode
    for(int i = off ; i < len + off ; ++i){
      write((int) cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    // we have to number every time the first line of a string.
    // then if we find if we had a \r and we did not had a new line.
    if(nbLine == 0 || (previousC == '\r' && c != '\n')){
      nbLine++;
      out.write(nbLine + "\t");
    }
    
    // then we write the caracter
    out.write(c);
    
    // whenever we have a '\n' we add anumber and the tab
    if(c == '\n'){
      nbLine++;
      out.write(nbLine + "\t");  
    }
    
    // we stock the new value of previousC
    previousC = c;    
  }

}
