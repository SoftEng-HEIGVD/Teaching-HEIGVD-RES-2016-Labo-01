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

  private int lastChar;    // Save the last character in the loop.
  private int numLine = 1; // Counter for the lines.

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

   /**
    * Reformat a string containing multiple lines by adding the number of the line in each beginning of line.
    * @param str String containing the text to reformat.
    * @param off Beginning of the part of the string to reformat.
    * @param len Length of the part of the string to reformat.
    */
  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(), off, len);
  }

   /**
    * Reformat an array of char representing a text containing multiple lines by adding the number of the line in each beginning of line.
    * @param cbuf String containing the text to reformat.
    * @param off Beginning of the part of the string to reformat.
    * @param len Length of the part of the string to reformat.
    */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for (int i = off; i < off + len; i++)
       write(cbuf[i]);
  }

   /**
    * Test if the caracter passed is an end of line and add the line numbering after it if necessary.
    * @param c The char to analyse.
    */
  @Override
  public void write(int c) throws IOException {
     // If it is the first line or there is a \r, we add the line number.
     if ((lastChar == '\r' && c != '\n') || numLine == 1) {
         out.write(numLine + "\t");
         numLine++;
     }

     // We write the current character.
     out.write(c);

     // If the current character is a '\n', we write the line number.
     if (c == '\n') {
        out.write(numLine + "\t");
        numLine++;
     }

     // We save the current character for the next loop turn.
     lastChar = c;
  }

}
