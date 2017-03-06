package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * we applied a filter that put all the characters to uppercase.
 *
 * @author Olivier Liechti
 * @modifiedBy Sebastien Boson
 */
public class UpperCaseFilterWriter extends FilterWriter {

  /**
   * Constructor for UpperCaseFilterWriter class.
   */
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  /**
   * This method write the specified String that start at the index off and have a length of len in
   * the underlying character-output stream.
   * It does a special treatment. When put all the characters to uppercase.
   *
   * @param str the String that will be written
   * @param off the start index of the String
   * @param len the length of what would be written from the String
   * @throws IOException
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    // we call the parent method with the String to uppercase
    super.write(str.toUpperCase(), off, len);
  }

  /**
   * This method write the specified array of char that start at the index off and have a length of len in
   * the underlying character-output stream.
   * It does a special treatment. When put all the characters to uppercase.
   *
   * @param cbuf the array of char that will be written
   * @param off the start index of the array of char
   * @param len the length of what would be written from the array of char
   * @throws IOException
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    // we call the parent method with a String to uppercase
    super.write(new String(cbuf).toUpperCase(), off, len);
  }

  /**
   * This method write the specified int in the underlying character-output stream.
   * It does a special treatment. When put all the characters to uppercase.
   *
   * @param c int to be written
   * @throws IOException
   */
  @Override
  public void write(int c) throws IOException {
    // we call the parent method with a char to uppercase
    super.write(Character.toUpperCase(c));
  }

}
