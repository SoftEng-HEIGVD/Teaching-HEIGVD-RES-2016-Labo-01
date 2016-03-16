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
  private int lineNumber =0;
  private char previousCharacter ='\0';
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
     for(int i=off; i < off+len;++i){
       this.write(str.charAt(i));
     }
    
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    this.write(new String(cbuf), off, len);
  }

  @Override
  public void write(int c) throws IOException {
     /*if it is the first line we write the number*/
     if(lineNumber==0){
        writeLineNumber();
     }
     /*if is the end of line*/
     if(c == '\n'){
        super.write('\n');
        /*if the character was not \r\n(end of line)*/
        if(previousCharacter != '\r'){
           writeLineNumber();  
        }
        /*if its \r\n */
        else{ 
           writeLineNumber();
        }
     }
     else {
        if(previousCharacter == '\r')
           writeLineNumber();
        super.write(c);
     }
     
     previousCharacter = (char) c;
  }
  /*this method convert the line number into string and write the it with the '\t'*/
  private void writeLineNumber() throws IOException{
     
      String lineNumberStr = Integer.toString(++lineNumber);
           for(int i =0; i< lineNumberStr.length();++i)
              super.write(lineNumberStr.charAt(i));
           super.write('\t');
  }

}
