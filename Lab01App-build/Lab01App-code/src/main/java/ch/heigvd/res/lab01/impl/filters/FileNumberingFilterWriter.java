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
 * Hello\n\World -> 1\tHello\n2\tWorld
 *
 * @author Olivier Liechti
 * @author Lucas Elisei (@faku99)
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

    // Used to store the lines count.
    private int lineCount = 0;

    // Used to know if the first char has already been treated or not.
    private boolean isFirstChar = true;

    // Used to know what was the last processed character.
    private int previousChar;

    public FileNumberingFilterWriter(Writer out) throws IOException {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        for (int i = off; i < off + len; ++i) {
            this.write(str.charAt(i));
        }
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        for (int i = off; i < off + len; ++i) {
            this.write(cbuf[i]);
        }
    }

    @Override
    public void write(int c) throws IOException {
        // If this is the first character to be processed, we write the line number
        // and set the flag to false.
        if (isFirstChar) {
            isFirstChar = false;
            this.printNewLineNumber();
        }

        // Every time we encounter the LF character, we start a new line.
        // Then, we have to check if the CR character is followed by the LF character.
        // If this is the case, then we have a CRLF character and handle it.
        // Otherwise, we treat the CR character as a new line.
        switch (c) {
            case '\n':
                super.write(c);
                printNewLineNumber();
                break;

            default:
                if(previousChar == '\r') {
                    printNewLineNumber();
                }

                super.write(c);
                break;
        }

        previousChar = c;
    }

    /**
     * Simply writes the line number and the tab character into the writer.
     *
     * @throws IOException
     */
    private void printNewLineNumber() throws IOException {
        super.out.write(String.format("%d\t", ++lineCount));
    }

}
