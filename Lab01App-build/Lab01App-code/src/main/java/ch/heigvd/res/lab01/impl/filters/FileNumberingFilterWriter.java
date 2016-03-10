package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Arrays;
import java.util.logging.Logger;

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
  
  private int lineNumber = 1;
  
  private boolean foundrreturn = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String[] lines = Utils.getNextLine(str.substring(off, off + len));
        // For the first write we always put the line number
        if(lineNumber == 1){
            out.write("1\t");
            lineNumber++;
        }
        while(!lines[0].isEmpty()){
            out.write(lines[0]);
            out.write(lineNumber++ + "\t");
            lines = Utils.getNextLine(lines[1]);
        }
        out.write(lines[1]);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(new String(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        // Always write the first line number
        if(lineNumber == 1){
            out.write("1\t");
            lineNumber++;
        }
        String[] lines = Utils.getNextLine(String.valueOf((char)c));
        // If there was a \r and next char is not a \n, add a line return
        if(foundrreturn && (char)c != '\n')
            out.write(lineNumber++ + "\t");
        foundrreturn = false;
        // If there is a \r wait for next char to see if it is a \n
        if((char)c == '\r'){
            out.write('\r');
            foundrreturn = true;
        }
        // If the char is not a \r write it
        else{
            if(!lines[0].isEmpty())
                out.write(lines[0] + lineNumber++ + "\t");
            out.write(lines[1]);
        }
    }

}
