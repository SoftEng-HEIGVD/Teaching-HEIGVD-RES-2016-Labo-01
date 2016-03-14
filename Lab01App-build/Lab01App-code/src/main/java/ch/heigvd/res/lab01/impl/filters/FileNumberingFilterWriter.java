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
  
  /*In order to complete the methods, two more variables were needed
   * - counter is used to manage the numbering correctly when we write more than once in the same writer
   * - r_found is used to manage the case of the \r\n separator int the write(int c) method
   */
  private char counter;
  private boolean r_found;
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
    counter = '0';
    r_found = false;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     //Select the part of the string we're working on
     str = str.substring(off, off+len);
     
     
     String output = "";
     if(!str.equals("")){
        //Start the numbering if this is the beginning  of the file
         if(counter == '0'){
            counter++;
            output = counter+"\t";
         }
         
         int start = 0;
         
         //Search for separators
         int index_n = str.indexOf("\n");
         int index_r = str.indexOf("\r");
         
         //If neither one is found, complete the output with the string and write it
         if(index_n == -1 && index_r == -1){
            output += str;
            out.write(output);
            return;
         }
         
         //Keep seraching for separators and complete the ouput with the counter 
         //and a portion of the string everytime we find one
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
     //Simply create a string and call the other method
     write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
     //Start the numbering if this is the beginning  of the file
     if(counter == '0'){
        counter++;
        out.write(counter);
        out.write("\t");
     }
     
     //If the past character was a \r and the separator is not \r\n, 
     //write the number before doing anything else
     if(r_found && c != '\n'){
        counter++;
        out.write(counter);
        out.write("\t");
        r_found = false;
     }
     
     out.write(c);
     
     //If a \n separator is found, write the number after the character
     if(c == '\n'){
        counter++;
        out.write(counter);
        out.write("\t");
        r_found = false;
        return;
     }
     
     //Remember \r in case the separator is \r\n
     if(c == '\r'){
        r_found = true;
        return;
     }
     
  }

}
