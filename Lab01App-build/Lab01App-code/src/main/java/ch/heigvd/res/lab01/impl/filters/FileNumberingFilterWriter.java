package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.InterruptedIOException;
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

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    write(str.toCharArray(),off,len);
  }


  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //write the correct offset
    for (int i = off; i < len + off; i++) {
      write(cbuf[i]);
    }
  }

  private int n = 2;
  private int[] h = {0,0};//keep history wor mac, windows compatibility
  @Override
  public void write(int c) throws IOException {
    if(h[1]=='\r' && c != '\n')//handle old MacIntosh + Windows specificity
      headerOfLine();

    out.write(c);

    if (c == '\n')//need to number the line when carriage return. UNIX + windows
      headerOfLine();

    h[1]=c; //keep history
    h[0]=h[1];
  }

  //function to add the required header at each line
  private void headerOfLine() throws IOException {
    String num = n + "";//handle if more than 1 digit
    out.write(num, 0, num.length());
    out.write('\t');
    n++;
  }

  {//at Initialisation, the file contains 1 line
    try {
      write(""+1+'\t');
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
