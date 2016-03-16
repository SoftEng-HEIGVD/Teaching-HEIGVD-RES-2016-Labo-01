package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import ch.heigvd.res.lab01.impl.*;

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

  int lines = 1;

  boolean retour = true;

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  // Wrapping of the writer
  // Writing in a writer with an edited String by the methode
  // I didn't call the other write method with a char, due to perf, if the user use unbuffered stream
  // it will take long time
  @Override
  public void write(String str, int off, int len) throws IOException
  {
    String rtn = new String();
    int rtnLen = len;

  if (lines == 1)
    {
      rtn += Integer.toString(lines++) + '\t';
      rtnLen += 2;
    }

    for (int i = off; i < len+off; ++i)
    {
      rtn += str.charAt(i);
      if (str.charAt(i) == '\n' || str.charAt(i) == '\r')
      {
        if (i+1 < str.length() && str.charAt(i+1) == '\n')
        {
          rtn += "\n";
          i += 1;
        }
        rtn += Integer.toString(lines++) + '\t';
        rtnLen += 2;
      }
    }

    super.write(rtn, 0, rtnLen);
  }

  // Same as the other one butt with a char[]
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String rtn = new String(cbuf);
    write(rtn, off, len);
  }


  // Same as the other one but for a single char
  // I could call the methode with string and cast the char but it will make a lof of
  // only once used objects
  @Override
  public void write(int c) throws IOException
  {
    String tmp = new String();
    if (retour && c != '\n')
    {
      tmp = Integer.toString(lines++) + "\t";
      super.write(tmp, 0 , tmp.length());
      retour = false;
    }

   else if (c == '\n')
    {
      retour = true;
    }

    else if (c == '\n' && retour)
      {
        super.write(c);
        retour = false;
      }
    else if (c == '\r')
      {
        retour = true;
      }

      super.write(c);

  }

}
