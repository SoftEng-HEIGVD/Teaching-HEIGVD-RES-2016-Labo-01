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

  /**
   * This method uppercase a range from a String
   * 
   * @author Ludovic Delafontaine
   * @param str the input String
   * @param off the beginning to uppercase
   * @param len the length to uppercase
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    // Create a new string
    String temp = new String(str.getBytes(), off, len);
            
    // Write characters by characters of the range to the output
    for (int i = 0; i < temp.length(); ++i) {
        write(temp.charAt(i));
    }
  }

  /**
   * This method uppercase a range from an array of characters
   * 
   * @author Ludovic Delafontaine
   * @param cbuf the input characters array
   * @param off the beginning to uppercase
   * @param len the length to uppercase
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // Create a new string
    String temp = new String(cbuf, off, len);
            
    // Write characters by characters of the range to the output
    for (int i = 0; i < temp.length(); ++i) {
        write(temp.charAt(i));
    }
  }

  /**
   * This method uppercase one character
   * 
   * @author Ludovic Delafontaine
   * @param c the character to uppercase
   */
  @Override
  public void write(int c) throws IOException {
    out.write(Character.toUpperCase(c));
  }

}
