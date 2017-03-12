package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 * 
 * EDIT by Nourazar Antoine
 */
public class UpperCaseFilterWriter extends FilterWriter {

   public UpperCaseFilterWriter(Writer wrappedWriter) {
      super(wrappedWriter);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
      
      // Convert to uppercase
      str = str.toUpperCase();
      
      // Use the mother's method
      super.write(str, off, len);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
      
      /* Convert to uppercase */
      for (int i = 0; i < cbuf.length; i++)
         cbuf[i] = Character.toUpperCase(cbuf[i]);
      
      
      //Use the mother's method
      super.write(cbuf, off, len);
      
   }

   @Override
   public void write(int c) throws IOException {
      
      // Convert to uppercase
      c = Character.toUpperCase(c);
      
      //Use mother's method
      super.write(c);
   }

}
