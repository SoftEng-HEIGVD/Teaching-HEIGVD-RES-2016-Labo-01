package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
   
   private int line = 2;
   private boolean isFirst = true;

   private boolean wasReturn = false;
   
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
      
      char ch = (char) c;
      String s = "";
      
      if(isFirst){
         super.write('1');
         super.write('\t');
         isFirst = false;
      }
      
      if(ch == '\n'){
         s += "\n" + line++ + "\t";
         wasReturn = false;
      } else if(ch == '\r'){
         wasReturn = true;
         s += "\r";
      } else{
         if(wasReturn){
            wasReturn = false;
            s+= line++ + "\t";
         }
         s += "" + ch;
      }
      
      for (char cha : s.toCharArray()) {
         super.write((int)cha);
      }
   }
   
   private String lineWrite (char[] cbuf, int off, int len){
      System.out.println("## START ##");
      
      String strReturn = "";
      if(isFirst){
         isFirst = false;
         strReturn = "1\t";
      }
      
      for (int j = off; j < off + len;j++) {
         char c = cbuf[j];
         if(c == '\r'){
            strReturn += "\r";
            System.out.print("\\r");
            if(cbuf[j + 1] == '\n'){
               System.out.print("\\n");
               strReturn += "\n";
               j++;
            }
            strReturn += line++ + "\t";
         }else if(c == '\n'){
            strReturn += "\n" + line++ + "\t";
            System.out.print("\\n");
         }
         else{
            System.out.print(c);
            strReturn += c;
         }
      }
      
      System.out.println("\n## AFTER  ##");
      return strReturn;
   }
}
