/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : Labo-01
 File         : UpperCaseFilterWriter.java
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

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      int i = off;
      //Save the upper cased String
      String upperCased = str.toUpperCase();
      //Write each char we want to write
      for(int lengthRemaining = len; lengthRemaining > 0; lengthRemaining--){
          super.write(upperCased.charAt(i));
          i++;
      }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      int i = off;
      //Save the upper cased String
      String upperCased = String.valueOf(cbuf).toUpperCase();
      //Write each char we want to write
      for(int lengthRemaining = len; lengthRemaining > 0; lengthRemaining--){
          super.write(upperCased.charAt(i));
          i++;
      }
  }

  @Override
  public void write(int c) throws IOException {
      //Write the single upper cased character
      super.write(Character.toUpperCase(c));
      
  }

}
