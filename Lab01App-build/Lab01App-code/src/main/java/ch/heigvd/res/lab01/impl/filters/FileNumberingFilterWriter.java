package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    private int count = 0;
    private boolean jump = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        count = 0;
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        String n = "";
        char lnjmp = (str.indexOf("\n") != -1 ? '\n' : '\r');

        str = str.substring(off, off+len);

        if(count == 0)
            n += String.valueOf(++count) + "\t";

        for(int i = 0; i < str.length(); i++){
            n += str.charAt(i);

            if(str.charAt(i) == lnjmp) {
                n += String.valueOf(++count) + "\t";
            }
        }

        out.write(n);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(cbuf.toString(), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        if(count == 0)
            out.write(String.valueOf(++count) + "\t");

        out.write((char)c);

        if((char)c == '\n'){
            out.write(String.valueOf(++count) + "\t");
        }
    }

}
