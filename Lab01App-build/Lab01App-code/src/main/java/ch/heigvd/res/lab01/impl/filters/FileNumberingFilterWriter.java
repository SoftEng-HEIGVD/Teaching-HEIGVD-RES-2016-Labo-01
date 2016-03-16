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
 * modified by Pascal Sekley
 */
public class FileNumberingFilterWriter extends FilterWriter {
   
   // This variable helps to count lines and gives a number to a line
   private int lineNumber = 0;

   // This helps to detect a '\n' in the line
   private boolean carriageReturn = false;
   

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  // We just convert the string in an array of char and then let the methode in charge
  // to make the treatment
  @Override
  public void write(String str, int off, int len) throws IOException {
  
     write(str.toCharArray(), off, len);
            
  }

  // We call the fonction that takes a char to make the treatment
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
     
     for( int i = off; i<off+len; i++)
        write(cbuf[i]);
     
  }

  @Override
  public void write(int c) throws IOException {
     
     // Convertion of the int to a char
     char currentChar = Character.toChars(c)[0];

     // If it's the beginning, the first line to be written we do it once
       if (lineNumber == 0) {
         out.write(++lineNumber + "\t");
        }

       // We write the line's number + '\t' when the char is none of '\r, \n and
       // a carriage retrun'
      if ((currentChar != '\r' && currentChar != '\n') && carriageReturn) {
         out.write('\r');
         out.write(++lineNumber + "\t");
         carriageReturn = false;
         out.write(currentChar);
      } 
      // If we encounter a \n, we set the flag to true so that we can produced the
      // right results in the test
      else if (currentChar == '\r') {
         carriageReturn = true;
      } 
      
      // The char is also writen when it's not a carriage return
      else if ((currentChar != '\r' && currentChar != '\n') && carriageReturn == false) {
         out.write(currentChar);
      } 

      
      // We write the following line when a '\n and a carriageReturn is found'
      else if (currentChar == '\n' && carriageReturn) {
         out.write('\r');
         carriageReturn = false;
         out.write('\n');
         out.write(++lineNumber + "\t");
      } 
      // We write the following line when a '\n and and a carriageReturn is not found'
      else if (currentChar == '\n' && carriageReturn == false) {
         out.write('\n');
         out.write(++lineNumber + "\t");
      }

   }

}
