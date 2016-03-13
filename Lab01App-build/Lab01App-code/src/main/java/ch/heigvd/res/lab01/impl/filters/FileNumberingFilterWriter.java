/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : Labo-01
 File         : FileNumberingFilterWriter.java
 Author       : Olivier Liechti, Guillaume Serneels
 Date         : 13.03.2016
 But          : Upper case filter to decorate the writer of the quote fetching 
                and treatment application
 -----------------------------------------------------------------------------------
*/

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
  
//Lines counter
  private static int linesCpt = '1';
  
  //Are we currently starting a new line with a '\r'(necessary for writing int by int)
  private static boolean newLineWithBackslashR = true;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    linesCpt = '1';

  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      int i = off;
      
      
      //Write the first line when necessary
      if(linesCpt == '1'){
          super.write(linesCpt);
          super.write('\t');      
          linesCpt++;
      }
      //Write each char with appropriate processing of new lines
      for(int lengthRemaining = len; lengthRemaining > 0; lengthRemaining--){
          super.write(str.charAt(i));
          if(str.charAt(i) == '\n' || str.charAt(i) == '\r'){
              
              if(str.charAt(i) == '\r' && (i + 1) <= str.length() && str.charAt(i + 1) == '\n'){
                  i++;
                  lengthRemaining--;
                  super.write(str.charAt(i));
              }
              super.write(linesCpt);
              super.write('\t');
              //Since we wrote a new line, we have to increment the lines counter
              linesCpt++;
          }
          i++;
      }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      String upperCased = String.valueOf(cbuf).toUpperCase();
      write(upperCased, off, len);

  }

  @Override
  public void write(int c) throws IOException {
      //Write the first line when necessary
      if(linesCpt == '1'){
          super.write(linesCpt);
          super.write('\t');      
          linesCpt++;
      }
      
      //Handling of a potential windows new line feed (\r\n)
      if(newLineWithBackslashR){
          if(c =='\n'){
              super.write(c);
              super.write(linesCpt);
              super.write('\t');
          }else{
              //After all, it was only a MacOSX new line feed (\r)
              super.write(linesCpt);
              super.write('\t');
              super.write(c);
          } 
          newLineWithBackslashR = false;
          linesCpt++;
          
      }else if(c == '\r'){
          super.write(c);
          newLineWithBackslashR = true;
      }else if(c == '\n'){
          //Handling a Unix new line feed (\n)
          super.write(c);
          super.write(linesCpt);
          super.write('\t');
          linesCpt++;
      }else{
          super.write(c);
      }
      

  }

}
