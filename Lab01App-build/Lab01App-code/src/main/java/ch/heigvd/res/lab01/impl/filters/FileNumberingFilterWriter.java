package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import ch.heigvd.res.lab01.impl.Utils;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\nWorld -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
  // attributes
  private int counter = 1;          // line counter
  private boolean numbered = false; // is the line already "numbered" ?
  private boolean carriage = false; // is the last char written a '\r' ?

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    // we split the string and send part to the effectiveWrite method
    String[] parts = Utils.getNextLine(str.substring(off, off + len));
    while(!parts[0].isEmpty()) {
      effectiveWrite(parts[0], true);
      parts = Utils.getNextLine(parts[1]);
    }
    effectiveWrite(parts[1], false);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    write(new String(cbuf), off, len); // delegate
  }

  @Override
  public void write(int c) throws IOException {
    write(new char[]{(char)c}, 0, 1); // delegate
  }

  /**
   * This method apply the filter and do the effective write
   * @param str string to be written
   * @param hasEOL is the string finished by an "end of line"
   */
  private void effectiveWrite(String str, boolean hasEOL) throws IOException {
    // we have to manage the "double EOL" ("\r\n") passed with write(int)
    if(carriage && str.equals("\n")) {
      numbered = true;
      carriage = false;
    }
    else if(str.equals("\r"))
      carriage = true;

    // if the string is empty and not already numbered, we "number" it
    if(str.isEmpty()) {
      if(!hasEOL && !numbered && !carriage) {
        String tmp = (counter++) + "\t";
        super.write(tmp, 0, tmp.length());
        numbered = true;
      }
    }

    // otherwise the string is not empty
    else {
      // if it is numbered, we simply write the string
      if(numbered) {
        super.write(str, 0, str.length());
        if(hasEOL)
          numbered = false;
      }
      // else we write a numbered string
      else {
        String tmp = (counter++) + "\t" + str;
        super.write(tmp, 0, tmp.length());
        if(!hasEOL)
          numbered = true;
      }
    }
  }
}
