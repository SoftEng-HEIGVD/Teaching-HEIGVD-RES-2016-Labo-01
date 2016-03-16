package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
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
  private int counter;
  private static int previousChar;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    String[] strings = Utils.getNextLine(str.substring(off, off+len));
    String newStr = "";

    //start numbering if at first line
    if(counter == 0)
      newStr += Integer.toString(++counter) + "\t";

    //number while there are still lines to be numbered
    while(!strings[0].equals("")) {
      newStr += strings[0] + Integer.toString(++counter) + "\t";
      strings = Utils.getNextLine(strings[1]);
    }
    //add last line
    newStr += strings[1];

    super.write(newStr,0,newStr.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String str = new String(cbuf);
    this.write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    //start numbering if at first line
    if(counter == 0)
      super.write(Integer.toString(++counter) + "\t");

    //if newline, continue numbering
    if(c == '\n') {
      super.write(c);
      super.write(Integer.toString(++counter) + "\t");
    }
    //otherwise check if previous character was a "\r". This is due to the fact that there is a "\r" and a
    //"\r\n" line return, which can only be handled by storing previous character and checking back
    else{
      if (previousChar == '\r')
        super.write(Integer.toString(++counter) + "\t");
      super.write(c);
    }

    previousChar = c;
  }

}
