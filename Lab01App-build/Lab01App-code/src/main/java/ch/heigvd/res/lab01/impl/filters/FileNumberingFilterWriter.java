package ch.heigvd.res.lab01.impl.filters;

import static ch.heigvd.res.lab01.impl.Utils.*;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer. When
 * filter encounters a line separator, it sends it to the decorated writer. It then
 * sends the line number and a tab character, before resuming the write process.
 *
 * Hello\nWorld -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Denise Gemesio
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

   private int number = 1;
   private boolean thereWasABackR = false;

   public FileNumberingFilterWriter(Writer out) {
      super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
      // The string is divided in chars inside an array and calls the function which
      // takes a chars array as parameter
      write(str.toCharArray(), off, len);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      // chars are sent one by one to the function which takes integers as parameter
      for (int i = 0; i < len; ++i) {
         write(cbuf[i + off]);
      }
   }

   @Override
   public void write(int c) throws IOException {
      // if it is the first char, then we first place the number, the tab and then the
      // char and we increase number
      if (number == 1) {
         out.write(Integer.toString(number));
         out.write("\t");
         out.write(c);
         number++;

      } else {

         // If there is a '\r' we can be in two situations:
         //    1. it's a solitary \r : after it, we will add the number and the tab
         //    2. it's a \r followed by a \n : after the \r, we write the \n, the
         //       number and the tab
         // So that's why we keep a track of the appearences of the \r!
         if (c == '\r') {
            thereWasABackR = true;
            out.write(c);

         } else if (c == '\n') {
            if (thereWasABackR) {
               thereWasABackR = false;
            }
            out.write(c);
            out.write(Integer.toString(number));
            out.write('\t');
            number++;

         } else {
            if (thereWasABackR) {
               thereWasABackR = false;
               out.write(Integer.toString(number));
               out.write('\t');
               number++;
            }
            out.write(c);
         }
      }

   }

}
