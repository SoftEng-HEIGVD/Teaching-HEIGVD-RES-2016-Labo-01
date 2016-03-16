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

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    private int line = 0;
    private boolean rbreak = false;

    @Override
    public void write(String str) throws IOException {
        super.write(str);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        write(str.toCharArray(),off,len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off+len; i++) {
            write((int) cbuf[i]); //write char by char with an offset
        }
    }

    @Override
    public void write(int c) throws IOException {
        char ch = (char) c;
        if (line == 0){
            writeNumberingTab();
        }
        if (ch == '\r') { //checks if we're on mac or windows system
            super.write(c);
            rbreak = true;
            // we don't write yet the new line number, waiting for the next character
        } else if (ch == '\n') { //adds a new line
            super.write(c);
            writeNumberingTab();
            rbreak = false; //if we're on windows, to have only one linebreak
        } else if(rbreak){ // if we're on mac system, because no \n is following the \r character
            writeNumberingTab();
            super.write(c);
            rbreak = false;
        } else {
            super.write(c); //write any character except the line breaks
        }
    }

    /**
     * Write the line number and a tab in the outputstream
     * @throws IOException
     */
    private void writeNumberingTab() throws IOException {
        out.write(++line+"\t");
    }

}
