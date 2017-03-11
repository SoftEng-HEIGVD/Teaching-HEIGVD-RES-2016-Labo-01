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
 * @author Xavier Vaz Afonso
 */
public class FileNumberingFilterWriter extends FilterWriter {

  //Declaration of variables
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private  int counter = 1;
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
          out.write(counter +"\t");
          firstLine = false;
      }

      //if the previous character was \r
      if(previousCharacter=='\r') {

          counter++;

         //if the current caracter is \n
         if((char)c=='\n') {
             out.write("\n"+counter + "\t");
             previousCharacter = '\n';
         }

         //if the current caracter isn't \n
         else if((char)c!='\n') {
             out.write(counter+"\t"+(char)c);
             previousCharacter = (char)c;
         }
      }

      //if the previous character wasn't \r and the current is \n
      else if((char)c=='\n') {
          counter++;
          out.write("\n"+counter+"\t");
          previousCharacter = (char)c;
      }
      //else
      else{
          out.write(c);
          previousCharacter = (char)c;
      }
  }
}
