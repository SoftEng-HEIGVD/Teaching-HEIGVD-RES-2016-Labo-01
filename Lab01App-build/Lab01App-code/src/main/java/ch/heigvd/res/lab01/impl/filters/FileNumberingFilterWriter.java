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
  private Boolean isPreviousMacEOL = false;
  private Boolean isPartialLineLog = false;
  private Boolean isNewLineLog = false;
  private int noLine = 1;
  private final String TABULATION = Character.toString('\t');
  
  
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }


  @Override
  public void write(String str, int off, int len) throws IOException {
     String tmp = str.substring(off, off + len);
     String strWellFormed = "";
     
     /***
      * Reinitialize isPartialLineLog in the beginning of the process
      * 
      */     
     if(out.toString().isEmpty()){
         isPartialLineLog = false;
         isNewLineLog = false;
         noLine = 1;
     }
     
     if(containEOF(tmp)){
        String[] strLines = Utils.getNextLine(tmp);
        while(!strLines[0].isEmpty()){
            /**
             * Check if process log if this method was evoked before with partial line
             */           
              if(isPartialLineLog){
                  strWellFormed += strLines[0];
                  isPartialLineLog = false;
                  
              }else if(isNewLineLog){
                  strWellFormed += strLines[0];
                  isNewLineLog = false;
              }else{
                  strWellFormed += addNumberOnTopString(strLines[0]);
              }
            strLines = Utils.getNextLine(strLines[1]);
        }    
        
        /**
         * handle the end of strWellFormed
         */
          strWellFormed += addNumberOnTopString(strLines[1]);          
          
          super.write(strWellFormed, 0, strWellFormed.length());
          
         /**
          * Memorize the last process state if the string argument is a line with separator('\n', '\r', '\r\n')
          */          
          isNewLineLog = true;
          isPartialLineLog = false;
          
     }else{
     

         if(isPartialLineLog || isNewLineLog){
             strWellFormed += tmp;
         }else
            strWellFormed = addNumberOnTopString(tmp);
         
         super.write(strWellFormed, 0, strWellFormed.length());
         
         /**
          * Memorize the last process state if the string argument is a partial line without separator('\n', '\r', '\r\n')
          */         
         isPartialLineLog = !isPartialLineLog;
     }
     
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
      this.write(new String(cbuf), off, len);
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }

  @Override
  public void write(int c) throws IOException {
    String tmp = Character.toString((char) c);
    
     /***
      * Reinitialize isPartialLineLog in the beginning of the process
      * 
      */     
     if(out.toString().isEmpty()){
         isPartialLineLog = false;
         isNewLineLog = false;
         noLine = 1;
        tmp = addNumberOnTopString(tmp);
        super.write(tmp, 0, tmp.length());
     }else if((char) c == '\n'){
        tmp += addNumberOnTopString("");
        super.write(tmp, 0, tmp.length());
    }else   
        super.write(c);

    
        
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");
  }
  
  private Boolean containEOF(String str){
      return (str.indexOf('\r') != -1) || (str.indexOf('\n') != -1);
  }
  
  private String addNumberOnTopString(String str){
      String tmp = Integer.toString(noLine) + TABULATION + str;
      noLine++;
      return tmp;
  }
}
