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

  private int i = 1;
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    boolean tabPlusEnter = false;
    int myLen = len;
    String myStr = "";
    // if beggining of the line, add 1\t
    if(i == 1) {
      myStr = Integer.toString(i) + "\t";
      i++;
      myLen += 2;
    }

    for(int k = off; k < (off+len); k++) {

      // look for \n or \r

      if (str.charAt(k) == '\n') {
          // add char
          myStr += str.charAt(k);
          myStr = myStr + Integer.toString(i) + "\t";
          i++;
          myLen += 2;
      }

      if (str.charAt(k) == '\r') {
        // add char
        myStr += str.charAt(k);
        if(str.length() > k+1 && str.charAt(k+1) == '\n') {
          // add char
          myStr += str.charAt(k+1);
          k++;
          tabPlusEnter = true;
        }
      }

      // if \r with or without \n after, add i\t
      if((k-1 >= 0 && str.charAt(k-1) == '\r') || tabPlusEnter) {
        myStr = myStr + Integer.toString(i) + "\t";
        i++;
        myLen += 2;
        tabPlusEnter = false;
      }

      // if \n or \r the char was already added
      if(str.charAt(k) != '\n' && str.charAt(k) != '\r') {
        // add char
        myStr += str.charAt(k);
      }
    }

    super.write(myStr, 0, myLen);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String myStr = new String(cbuf);
    write(myStr, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    write(Character.toString((char)c), 0, 1);
  }

}
