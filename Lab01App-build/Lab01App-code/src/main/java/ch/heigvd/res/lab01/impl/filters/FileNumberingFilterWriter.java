package ch.heigvd.res.lab01.impl.filters;

import com.sun.org.apache.xpath.internal.operations.Bool;

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
    private int lineNumber = 1;
    private Boolean waitingForN = false;
    public FileNumberingFilterWriter(Writer out) {
        super(out);
        try {
            super.write(Character.forDigit(lineNumber, 10));
            super.write('\t');
            lineNumber++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        if(off < len -1) {
            for (int i = off; i < off + len; i++) {
                char c = str.charAt(i);
                if (c == '\n' || c == '\r') {
                    if (c == '\r') {
                        super.write('\r');
                        if (str.charAt(i + 1) == '\n') {
                            super.write('\n');
                            i++;
                        }
                    } else {
                        super.write('\n');
                    }
                    super.write(Character.forDigit(lineNumber, 10));
                    lineNumber++;
                    super.write('\t');
                } else {
                    super.write(c);
                }

            }
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
//        for(int i = off; i < len; i++) {
//            if(cbuf[i] == '\r')
//                if(i < len && cbuf[i+1] == '\n')
//                    if ( i > off)
//                        write(cbuf.)
//        }
    }

    @Override
    public void write(int c) throws IOException {
        // "This is line 1\r\nThis is line 2\nThis is line 3"
        if(waitingForN) {
            if((char) c == '\n') {
                super.write('\r');
                super.write('\n');
                super.write(Character.forDigit(lineNumber, 10));
                lineNumber++;
                super.write('\t');
            } else {
                super.write('\r');
                super.write(Character.forDigit(lineNumber, 10));
                lineNumber++;
                super.write('\t');
                super.write(c);
            }
            waitingForN = false;
            return;
        }
        if((char) c == '\r')
            waitingForN = true;
        if(!waitingForN) {
            if((char) c == '\n') {
                super.write(c);
                super.write(Character.forDigit(lineNumber, 10));
                lineNumber++;
                super.write('\t');
            } else {
                super.write(c);
            }
        }
    }

}
