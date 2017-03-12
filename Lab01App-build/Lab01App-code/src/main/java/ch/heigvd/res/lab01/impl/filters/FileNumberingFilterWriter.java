package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer. When
 * filter encounters a line separator, it sends it to the decorated writer. It then
 * sends the line number and a tab character, before resuming the write process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
   
   private int line = 2; // static number of lines (the first is staticly put)
   private boolean isFirst = true;  // to know when to put the first line

   private boolean wasReturn = false; // check if previous char was a \r
   
   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
            
      str = lineWrite(str.toCharArray(), off, len);
      
      super.write(str, 0, str.length());
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      
      cbuf = lineWrite(cbuf, off, len).toCharArray();
      
      super.write(cbuf, off, len);
   }

   @Override
   public void write(int c) throws IOException {
      
      char character = (char) c;
      String output = "";
      
      // first character, we add the first line marker
      if(isFirst){
         super.write('1');
         super.write('\t');
         isFirst = false;
      }
      
      // if we find \n, we put the line in all cases
      // if we find \r, we put \r and wait for the next character to know what to do
      // if it's anything else, we put the character, with the rest of the line if
      // \r was the previous character
      if(character == '\n'){
         output += "\n" + line++ + "\t";
         wasReturn = false;
      } else if(character == '\r'){
         wasReturn = true;
         output += "\r";
      } else{
         if(wasReturn){
            wasReturn = false;
            output+= line++ + "\t";
         }
         output += "" + character;
      }
      
      // we write all the characters
      for (char cha : output.toCharArray()) {
         super.write((int)cha);
      }
   }
   
   /**
    * Write the buffer array delimited with the offset and length
    * and return a string with the line number before each line
    * @param cbuf an array of chars to write (can be a string converted)
    * @param off the starting character
    * @param len the lenght of the part to filter
    * @return the string filtered (can be converted to a charArray)
    */
   private String lineWrite (char[] cbuf, int off, int len){
      
      String strReturn = "";
      
      // first line to write
      if(isFirst){
         isFirst = false;
         strReturn = "1\t";
      }
      
      // we filter only the part to filter
      for (int j = off; j < off + len;j++) {
         char character = cbuf[j];
         // if we find a \r, we check if the next is a \n and act accordingly
         // by incrementing j again to skip the second character
         // else we write the number of the line for each line
         if(character == '\r'){
            strReturn += "\r";
            System.out.print("\\r");
            if(cbuf[j + 1] == '\n'){
               System.out.print("\\n");
               strReturn += "\n";
               j++;
            }
            strReturn += line++ + "\t";
         }else if(character == '\n'){
            strReturn += "\n" + line++ + "\t";
            System.out.print("\\n");
         }
         else{
            System.out.print(character);
            strReturn += character;
         }
      }
      
      return strReturn;
   }
}
