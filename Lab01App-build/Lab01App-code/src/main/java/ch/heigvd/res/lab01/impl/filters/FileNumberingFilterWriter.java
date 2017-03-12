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
 * Hello\nWorld -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Nathan Gonzalez Montes
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  int nbLines = 1;                  // The number of lines of the string
  boolean doneOnce = false;         // When we do the iteration for the first time
  boolean wasBackSlashR = false;    // The character before was a '\r'
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }
  
  /**
   * Writes a portion of a string with the number of line and '\t' at the begining
   * @param str String to be written
   * @param off Offset from witch to start reading characters
   * @param len Number of characters to be written
   * @throws IOException 
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
      // Calls the write method with the parameter int
      for(int i = off; i < off + len; ++i){
          write(str.charAt(i));
      }
  }
  
  /**
   * Writes a portion of an array of characters with the number of line and '\t' at the begining
   * @param cbuf Buffer of characters to be written
   * @param off Offset from witch to start reading characters
   * @param len Number of characters to be written
   * @throws IOException 
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      // Calls the write method with the parameter int
      for(int i = off; i < len; ++i){
          write((int)cbuf[i]);
      }
  }
  
  /**
   * Writes the line number and '\t' when it's necessary
   * @param c the current character of the string
   * @throws IOException 
   */
  @Override
  public void write(int c) throws IOException {
      // If we do the first iteration of the string
      if(!doneOnce){
          out.write(nbLines + "\t"); // We write the line number and '\t'
          doneOnce = true;
      }
      // If the character before was a '\r'
      if(wasBackSlashR){
          // If the current character is a '\n'
            if((char)c == '\n'){
                out.write(c); // We write the character '\n'
                ++nbLines;
                out.write(nbLines + "\t"); // We write the line number and '\t'
                wasBackSlashR = false;
                return;
            }
            // If it was just a \r and the current isn't a \n
            ++nbLines;
            out.write(nbLines + "\t"); // We write the line number and '\t'
            wasBackSlashR = false;
            // If it's just a '\n'
      }else if((char)c == '\n'){
        out.write(c); // We write the character '\n'
        ++nbLines;
        out.write(nbLines + "\t"); // We write the line number and '\t'
        return;
      }
      // If we find a '\r', we need to know if the next is a '\n', so we use the
      // boolean to tell us that there was a '\r' and use it after
      if((char)c == '\r'){
          wasBackSlashR = true;
      }
      
      out.write(c); // We write the current character
  }
}
