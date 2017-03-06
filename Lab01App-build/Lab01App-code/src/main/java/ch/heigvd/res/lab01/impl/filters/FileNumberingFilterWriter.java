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

  private int count;
  private boolean first;
  String currentSeparator;
  char memory = ' ';
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    count = 2;
    first = true; 
    currentSeparator = "";
    memory = ' ';
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      
      String tmpStr = str.substring(off, len+off); // we get the valable string that are going to be write
      String[] lines = Utils.getNextLine(tmpStr);
      if(first){
          super.out.append("1\t"); // first line of the test
          first = false;
      }
      while(!lines[0].isEmpty()){ // The string containt a least one separator line
         super.out.append(lines[0]);
         super.out.append(count++ + "\t");   // write the number of the current line     
         lines = Utils.getNextLine(lines[1]); // we check if there oder line separator
      }
      super.out.append(lines[1]); // then we write the reste of the string to the writer

      
   
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      write(String.valueOf(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
      
      if(first){
          super.out.append("1\t");  // first line of the test
          first = false;
      }
      switch(c){
          case '\n' : {
              if(memory == '\r'){ // we check if the previous caractere is a \r line separator
                  super.out.append("\r\n"); // if true then the current line separator is a windows line separator \r\n
                  super.out.append(String.valueOf(count++)+"\t");
                  memory = ' '; // we change the momory in oder avoid mistake in the next caractere
              }else if(memory == ' '){ // if false the current line separator is \n 
                  super.out.append("\n");
                  super.out.append(String.valueOf(count++) + "\t");
              }
              
              break;
          }
          case '\r' : {
              memory =(char)c; // we keep this line separator to check in the next caractere if we have \r or \r\n
              break;
          }
          default : { // the current caractere is not a line separator
              
             if(memory == '\r'){
                super.out.append("\r"); // if true we need to write the previous line saparator 
                super.out.append(String.valueOf(count++) + "\t"); 
             }
             super.out.append(String.valueOf((char)c));
             memory = ' ';
             
          }
              
              
      }
      
      
  }

}
