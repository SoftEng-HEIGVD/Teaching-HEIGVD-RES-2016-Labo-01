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
 * 
 * EDITED BY Antoine Nourazar
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

   /* Members added to use with the methods */
   private boolean lseparator = false;
   private boolean start = true;
   private int line = 1;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {

      for (int i = off; i < (off + len); i++) {

         this.write(str.charAt(i));
      }

   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {

      for (int i = off; i < off + len; i++) {
         this.write(cbuf[i]);
      }
   }
   
   
   /** This method will be called by the others
    *  It will manage the case with \r, \r\n or \n
    * 
    * @param c
    * @throws IOException 
    */
   @Override
   public void write(int c) throws IOException {
      
      /* First if we want to write the first character, we to got the write
       * the line number and a tabulation */
      if (start && line != '\r' && line != '\n' ) {

         out.write(Integer.toString(line));
         out.write('\t');
         out.write(c);
         start = false;
      } 
      //if it's not the first character, we're looking if it's a line separator
      else {
         
         // if it's a '\r', we can't already write it, we have to check the next
         // char. So we store with a boolean the fact we met a '\r'...
         if (c == '\r')
            lseparator = true;
         
         
         else {
            
            if (c == '\n') {
               
               /*  if the current char is a '\n', we're lookin if there was a '\r'
                *  before. Because we didn't write it before, so we have to write it
                *  now. Then we just have to write the '\n', the line number and 
                *  the  '\t' 
                */
               if (lseparator) {

                  out.write('\r');
                  lseparator = false;
               }

               out.write(c);
               out.write(Integer.toString(++line));
               out.write('\t');
            } 
            
            /* if it's not a '\n', then it's a normal char, we just have to check
             * if there was a '\r' before just to write it with the line and the
             * tab, otherwise, we just write the char...  
            */
            else {
               
               if (lseparator) {
                  
                  out.write('\r');
                  out.write(Integer.toString(++line));
                  out.write('\t');
                  lseparator = false;
               }
               out.write(c);
            }
         }
      }
   }
}
