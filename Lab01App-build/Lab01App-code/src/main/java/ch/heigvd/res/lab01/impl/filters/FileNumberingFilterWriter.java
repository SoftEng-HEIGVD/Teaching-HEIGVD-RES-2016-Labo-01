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
 */
public class FileNumberingFilterWriter extends FilterWriter {

  //Declaration of variables
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private  int cpt = 1;
  private  boolean firstLine = true;
  private char previousCharacter = '0';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

      write(str.toCharArray(),off,len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

      for(int x = off; x<(off+len);x++)
      {
          write(cbuf[x]);
      }
  }

  @Override
  public void write(int c) throws IOException {

      //The firstTime
      if(firstLine) {
          out.write(cpt +"\t");
          firstLine = false;
      }

      if(previousCharacter=='\r') {

          cpt++;

         if((char)c=='\n')
         {
             out.write("\n"+cpt + "\t");
             previousCharacter = '\n';
         }

         else if((char)c!='\n')
         {
             out.write(cpt+"\t"+(char)c);
             previousCharacter = (char)c;
         }
      }

      else if((char)c=='\n')
      {
          cpt++;
          out.write("\n"+cpt+"\t");
          previousCharacter = (char)c;
      }
      else{
          out.write(c);
          previousCharacter = (char)c;
      }
  }
}
