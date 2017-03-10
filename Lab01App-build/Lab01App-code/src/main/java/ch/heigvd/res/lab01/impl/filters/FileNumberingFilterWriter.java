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
 * @author Pierre-Benjamin Monaco
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNumber = 0;
  private boolean macOSDetection = false;


  public FileNumberingFilterWriter(Writer out) {
    super(out);
    addNewLine(); //Always add a first line (even if write is never called)
  }

  /*
    Simple method to add new line and safely call the lineNumber attribute
    Handle the IOException for append(String) method with a log warning
   */
  public void addNewLine()
  {
    try
    {
      out.write(++lineNumber + "\t");
    }
    catch (IOException e)
    {
      LOG.warning("FileNumberingFilterWriter#addNewLine() : " + e);
    }
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    //Changes the string into charArray and send it to write(char[] cbuf, ...)
    write(str.toCharArray(),off,len);

  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    //Reads the char array and write char by char with write(int c)
    for(int reader = off; reader < off + len; ++reader)
    {
      write(cbuf[reader]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    char cAsChar = (char)c;

    //Checks if the last char was \r with macOSDetection. Also verify is actual char
    //is not \n to avoid double new line
    if(macOSDetection && cAsChar != '\n')
    {
      addNewLine();
      macOSDetection = false; //Disable MacOsDetection signal
    }

    //Writes the char in the output
    out.write(cAsChar);

    //Detect \r char in case of mac encoding
    if(cAsChar == '\r')
    {
      macOSDetection = true;
    }
    //Add new line in case of windows or unix encoding
    else if(cAsChar == '\n')
    {
      addNewLine();
      macOSDetection = false; //Disable MacOsDetection signal
    }
  }

}
