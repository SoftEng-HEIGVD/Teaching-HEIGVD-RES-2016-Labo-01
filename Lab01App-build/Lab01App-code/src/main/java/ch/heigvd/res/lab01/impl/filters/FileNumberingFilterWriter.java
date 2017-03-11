package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import ch.heigvd.res.lab01.impl.Utils;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 * Modified by Lara Chauffoureaux on 08.03.2017
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineNumber;
    private int lastChar;
    
    public FileNumberingFilterWriter(Writer out) {
        super(out);
        lineNumber = 1;
        lastChar = 0;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        
        if(str.equals("")) {
            
            return;
        }

        String wantedSubstring = str.substring(off, off + len);
        String[] lines = Utils.getNextLine(wantedSubstring);
        
        // if it's the first thing to print in this writer, we print the 
        // 1st line number (1\t)
        if(lineNumber == 1) {

            printLineNumber();  
        }
                
        while(lines[0].equals("") == false) {
        
            // if the while is entered, it means that there's a line with a 
            // separator at the end -> another line after this one. Thus the line +
            // the next line number are printed
            out.write(lines[0]);
            printLineNumber();
            
            lines = Utils.getNextLine(lines[1]);
        }
        
        // don't forget to print the rest of the text
        out.write(lines[1]);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

        // Re-use the beautiful function write with string implemented above
        String cbufStr = new String(cbuf);
        write(cbufStr, off, len);
    }

    @Override
    public void write(int c) throws IOException {

        // if it's the first thing to print in this writer, we print the 1st line
        // number (1\t)
        if(lineNumber == 1) {
        
            printLineNumber();
        }
        
        // in case i had a \r with now no \n (\r already printed), it's the
        // begining of a new line 
        if((lastChar == '\r' && c != '\n')) {
            
            printLineNumber();
        }
        
        out.write(c);

        // if it's a \n (in the \r\n case or \n case, it changes nothing), we 
        // set the begining of a new line. This need to be done after the \n
        // printing
        if(c == '\n') {
                
            printLineNumber();
        }
        
        lastChar = c;
    }
    
    /*
     * This method just print in the writer the line number corresponding to the
     * private field of the class. 
     */
    private void printLineNumber() throws IOException {
        
        String lineNumberStr = Integer.toString(lineNumber) + '\t';
        lineNumber++ ;
        
        out.write(lineNumberStr);
    }
}
