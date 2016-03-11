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
 * Modified by Henrik Akesson
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
    //Stores the current line Number for the instance
    private int lineNumber = 1;
    //This boolean is useful if the characters "\r\n" are received in the write(int c) method
    private Boolean waitingForN = false;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        //We start with a '1' and a tab character
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

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; i++) {
            char c = cbuf[i];
            if (c == '\n' || c == '\r') {
                if (c == '\r') {
                    super.write('\r');
                    if (cbuf[i + 1] == '\n') {
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

    @Override
    public void write(int c) throws IOException {
        //If a '\r' had been read before
        if (waitingForN) {
            //If a '\n' is read, then we write "\r\n"
            if ((char) c == '\n') {
                super.write('\r');
                super.write('\n');
                super.write(Character.forDigit(lineNumber, 10));
                lineNumber++;
                super.write('\t');
            } else { //We write '\r' and the received char
                super.write('\r');
                super.write(Character.forDigit(lineNumber, 10));
                lineNumber++;
                super.write('\t');
                super.write(c);
            }
            waitingForN = false;
            return;
        }
        if ((char) c == '\r')
            waitingForN = true;
        if (!waitingForN) {
            if ((char) c == '\n') {
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
