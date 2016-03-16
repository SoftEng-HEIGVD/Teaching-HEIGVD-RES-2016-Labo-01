/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : 1
 File         : FileNumberingFilterWriter.java
 Author       : Antoine Drabble
 Date         : 10.03.2016
 Goal         : Add a line number to every line written by a decorated writer.
 -----------------------------------------------------------------------------------
*/
package ch.heigvd.res.lab01.impl.filters;

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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int lineNumber = 1;

    private boolean rReturnFound = false;

    public FileNumberingFilterWriter(Writer out) {
      super(out);
    }

    /**
     * Write the line numbers for a string
     * 
     * @param str
     * @param off
     * @param len
     * @throws IOException 
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        String s = str.substring(off, off + len);
        for (int i = 0; i < s.length(); i++){
            write(s.charAt(i));
        }
        
        // Version without write(int c);
        /*String[] lines = Utils.getNextLine(str.substring(off, off + len));
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
        out.write(lines[1]);*/
    }

    /**
     * Write the line numbers for a char array
     * @param cbuf
     * @param off
     * @param len
     * @throws IOException 
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(new String(cbuf), off, len);
    }

    /**
     * Write the line numbers for a char
     * @param c
     * @throws IOException 
     */
    @Override
    public void write(int c) throws IOException {
        // Always write the first line number
        if(lineNumber == 1){
            out.write("1\t");
            lineNumber++;
        }
        // If there was a \r and next char is not a \n, add a line return
        if(rReturnFound && (char)c != '\n'){
            out.write(lineNumber++ + "\t");
        }
        rReturnFound = false;
        // If there is a \r wait for next char to see if it is a \n
        if((char)c == '\r'){
            out.write('\r');
            rReturnFound = true;
        }
        // If the char is not a \r write it
        else{
            out.write((char)c);
            if(c == '\n' || c == '\r'){
                out.write(lineNumber++ + "\t");
            }
        }
    }
    
    @Override
    public void close() throws IOException{
        super.close();
        // Write the line return if there was a \r at the end of the content
        if(rReturnFound){
            out.write(lineNumber + "\t");
        }
    }
}
