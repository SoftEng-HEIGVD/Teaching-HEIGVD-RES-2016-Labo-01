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
 * <p>
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    private int lineNumber = 0;
    private boolean wasLastReturn = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        int endIndex = off + len;
        String subString;
        if (endIndex >= str.length()) {
            subString = str.substring(off);
        } else {
            subString = str.substring(off, endIndex);
        }
        String tmpString = "";
        int i = 0;
        if (wasLastReturn) {
            wasLastReturn = false;
        }
        if (lineNumber == 0) {
            tmpString = ++lineNumber + "\t";
        }
        for (; i < subString.length(); i++) {
            if(wasLastReturn){
                tmpString += ++lineNumber + "\t";
                wasLastReturn = false;
            }
            tmpString += subString.charAt(i);
            if (subString.charAt(i) == '\n') {
                tmpString += ++lineNumber + "\t";
            } else if (subString.charAt(i) == '\r') {
                if (!(i != subString.length() - 1 && subString.charAt(i + 1) == '\n')) {
                    wasLastReturn = true;
                }
            }
        }
        out.write(tmpString);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        write(new String(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        write(Character.toString((char) c), 0, 1);
    }

}
