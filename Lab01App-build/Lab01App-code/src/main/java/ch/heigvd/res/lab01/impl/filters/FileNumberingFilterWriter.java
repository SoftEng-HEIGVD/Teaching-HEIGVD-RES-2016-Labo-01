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
    int myLen = len;
    String myStr = "";
    // if it's the beginning of the line, add 1\t
    // and add 2 for the length
    if(i == 1) {
      myStr = Integer.toString(i) + "\t";
      i++;
      myLen += 2;
    }

    // go though the string str
    // and add char after char to add in proper place i\t
    for(int k = off; k < (off+len); k++) {

      // look for \n or \r

      // if it's a \n, add 1\t
      // and add 2 for the length
      if (str.charAt(k) == '\n') {
          // add char
          myStr += str.charAt(k);
          myStr = myStr + Integer.toString(i) + "\t";
          i++;
          myLen += 2;
      }

      // if it's a \r, check if there is a \n after it
      if (str.charAt(k) == '\r') {
        // add char
        myStr += str.charAt(k);
        // if there are \n following a \r don't add twice i\t but only once.
        if(str.length() > k+1 && str.charAt(k+1) == '\n') {
          // add char
          myStr += str.charAt(k+1);
          k++;
        }
      }

      // if \r without \n after, add i\t
      // and add 2 for the length
      if(k-1 >= 0 && str.charAt(k-1) == '\r') {
        myStr = myStr + Integer.toString(i) + "\t";
        i++;
        myLen += 2;
      }

      // if it's a \n or a \r the char was already added
      // if it's not a \r or a \n the char need to be add
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
