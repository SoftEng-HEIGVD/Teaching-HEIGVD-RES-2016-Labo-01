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

  //Declaration of variables
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private  int cpt = 1;
  private  boolean firstLine = true;
  private char previousCharacter = '0';

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    str = str.substring(off,off+len); //With that, it can be used by a part of a string

    StringBuffer newStr = new StringBuffer();
    String[] newLines;

    //The first time ( Because every text has at least one line)
    if(firstLine)
    {
        newStr.append(cpt+"\t");
        firstLine = false;
    }

    while(true)
    {
        newLines = Utils.getNextLine(str);

        //There are no more lines
        if(newLines[1].equals(str))
        {
            newStr.append(newLines[1]);
            break;
        }

        //There are still lines
        else
        {
            newStr.append(newLines[0]);

            cpt++;
            newStr.append(""+cpt+"\t");

            str = newLines[1];
        }
    }

    super.write(newStr.toString(), 0, newStr.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

      String test = new String(cbuf);
      write(test,off,len);
  }

  @Override
  public void write(int c) throws IOException {

      //At first, i thought i can used the write function which have a string in parameter
      //but it was difficult with the line separator. \r\n it's note a character
      //but two  '\r' and '\n'. So it was impossible for me to make the difference.

      //The firstTime
      if(firstLine)
      {
          cpt = 49; //It's the 1 in ASCII
          super.write(cpt);
          super.write('\t');
          firstLine = false;
      }

      if(previousCharacter=='\r')
      {
         if((char)c=='\n')
         {
             super.write('\n');
             cpt++;
             super.write(cpt);
             super.write('\t');
             previousCharacter = '\n';
         }

         else if((char)c!='\n')
         {
             cpt++;
             super.write(cpt);
             super.write('\t');
             super.write(c);
             previousCharacter = (char)c;
         }
      }

      else if((char)c=='\n')
      {
          super.write('\n');
          cpt++;
          super.write(cpt);
          super.write('\t');
          previousCharacter = (char)c;
      }
      else{
          super.write(c);
          previousCharacter = (char)c;
      }
  }
}
