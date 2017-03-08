package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.StringTokenizer;
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
  private int lineNumber = 0;
  private boolean macOSDetection = false;


  public FileNumberingFilterWriter(Writer out) {
    super(out);
    addNewLine();
  }

  /*
    Simple method to add new line and safely call the lineNumber attribute
    Handle the IOException for append(String) method with a log warning
   */
  public void addNewLine()
  {
    try
    {
      out.append(++lineNumber + "\t");
    }
    catch (IOException e)
    {
      LOG.warning("FileNumberingFilterWriter#addNewLine() : " + e);
    }
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    write(str.toCharArray(),off,len);

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    for(int reader = off; reader < off + len; ++reader)
    {
      write(cbuf[reader]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    char cAsChar = (char)c;

    if(macOSDetection && cAsChar != '\n')
    {
      addNewLine();
      macOSDetection = false;
    }
    out.append(cAsChar);

    if(cAsChar == '\r')
    {
      macOSDetection = true;
    }
    else if(cAsChar == '\n')
    {
      addNewLine();
      macOSDetection = false;
    }
  }

}
