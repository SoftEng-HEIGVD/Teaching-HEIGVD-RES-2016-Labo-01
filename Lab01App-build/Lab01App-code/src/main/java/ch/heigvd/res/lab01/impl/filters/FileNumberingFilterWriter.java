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
    private int lineNumber = 0;
    private boolean isDetectedR = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        StringBuilder strTmp = new StringBuilder(str.substring(off, off + len));
        final String tabulator = "\t";
        final char nextLine = '\n';
        final char backSpace = '\r';

        if (lineNumber == 0) {
            strTmp.insert(0, ++lineNumber + tabulator);
        }

        int i = 0;
        while(i < strTmp.length()){
        
            if (strTmp.charAt(i) == nextLine) {
                lineNumber++;
                strTmp.insert(i + 1, lineNumber + tabulator);
            } else if (strTmp.charAt(i) == backSpace) {

                if (i < strTmp.length() && strTmp.charAt(i + 1) == nextLine) {
                    i++;
                }
                lineNumber++;
                strTmp.insert(i + 1, lineNumber + tabulator);
            }
            i++;
        }

        out.write(strTmp.toString());
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.write(new String(cbuf), off, len);
    }

    @Override
    public void write(int c) throws IOException {
        if (lineNumber == 0 || (isDetectedR && c != '\n')) {
            lineNumber++;
            out.write(lineNumber + "\t");
        }

        if (isDetectedR) {
            isDetectedR = false;
        }

        out.write(c);

        if (c == '\r') {
            isDetectedR = true;
        } else if (c == '\n') {
            out.write(++lineNumber + "\t");
        }
    }

}
