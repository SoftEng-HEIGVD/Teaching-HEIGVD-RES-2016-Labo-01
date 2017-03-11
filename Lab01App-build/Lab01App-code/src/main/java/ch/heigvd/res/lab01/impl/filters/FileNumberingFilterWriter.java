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
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {
    
    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int noLine;
    private boolean returnLine;
    
    public FileNumberingFilterWriter(Writer out) {
        super(out);
        this.noLine = 1;
        this.returnLine = false;
        try{
            super.write(noLine + "\t");
        }catch(IOException e){
            System.err.println(e);
        }
    }
    
    @Override
    public void write(String str, int off, int len) throws IOException {
        for(int i = off; i < off + len; i++){
            this.write(str.charAt(i));
        }
    }
    
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.write(new String(cbuf), off, len);
    }
    
    @Override
    public void write(int c) throws IOException {
        char charactere = (char)c;
        
        if(returnLine && charactere != '\n'){
            out.write(++noLine + "\t");
        }
        
        out.write(charactere);
        
        switch(charactere){
            case '\n':
                out.write(++noLine + "\t");
                returnLine = false;
                break;
            case '\r':
                returnLine = true;
                break;
            default:
                returnLine = false;
        }
        
    }
    
}
