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
  private int LINE_CPT = 1;
  private boolean FIRST_TIME = true;
  private String STR_TO_WRITE = "";
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

      str = str.substring(off, off+len);

      String[] tmpTab = Utils.getNextLine(str);
      
      
       if(FIRST_TIME == true){
          STR_TO_WRITE += LINE_CPT + "\t";
          FIRST_TIME = false;
       }
       
       // So we know that str dont contain an EOL char.
       if(tmpTab[0].equalsIgnoreCase("")){
           STR_TO_WRITE += tmpTab[1];
           
           // Dont forget to re-set the string lenght. 
           len = STR_TO_WRITE.length();
           out.write(STR_TO_WRITE, 0, len);
           STR_TO_WRITE = "";
       }//  In this case str contain an EOL char
       else if(tmpTab[1].equalsIgnoreCase("")){
           LINE_CPT++;
           STR_TO_WRITE += tmpTab[0] + LINE_CPT + "\t";
           
           // Dont forget to re-set the string lenght. 
           len = STR_TO_WRITE.length();
           out.write(STR_TO_WRITE, 0, len);
           STR_TO_WRITE = "";
       }// In this case str contain more than one string.
       else{
          LINE_CPT++; 
          STR_TO_WRITE += tmpTab[0] + LINE_CPT + "\t";
          this.write(tmpTab[1]);

       }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
        
        String tmp = "";
        int i = 0;
        while (i < len) {
            tmp += cbuf[i + off];
            i++;
        }
        this.write(tmp);
  }

  @Override
  public void write(int c) throws IOException {
      
      char tmpChar = (char)c;
      this.write(Character.toString(tmpChar));
  }

}
