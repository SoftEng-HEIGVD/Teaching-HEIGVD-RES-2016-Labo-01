package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import ch.heigvd.res.lab01.impl.Utils;
import java.util.logging.Level;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int currentLineNumber;
  private int previousChar;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    currentLineNumber = 1;
      try {
          out.write(currentLineNumber + "\t");
      } catch (IOException ex) {
          Logger.getLogger(FileNumberingFilterWriter.class.getName()).log(Level.SEVERE, null, ex);
      }
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
      String[] lines = new String[2];
      String result = "";
      
      // Ce placer au bon offset
      lines[1] = str.substring(off, off + len);
      
      do {
          lines = Utils.getNextLine(lines[1]);
          
          if (lines[0].equals("")) {
              result += lines[1];
              break;
          }
          else {
              result += lines[0];
              
              if (lines[0].endsWith("\n") || lines[0].endsWith("\r")) {
                  currentLineNumber++;
                  result += currentLineNumber + "\t";
              }           
          }
      } while(!lines[0].equals(""));

      super.write(result, 0, result.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
      if (c == '\r') {
          previousChar = '\r';
          return;
      }
      else if (c == '\n' && previousChar == '\r') {
          previousChar = c;
          write("\r\n", 0, 2);
          return;
      }
      else if (c != '\n' && previousChar == '\r') {
          write(new String(Character.toChars('\r')), 0, 1);
      }
      
      write(new String(Character.toChars(c)), 0, 1);
  }

}
