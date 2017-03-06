package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;
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
    private int counter = 0; // this is the number of the line that is currently read
    private String buffer = new String(); 
    private boolean multiple = false;
    private boolean first = true;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    /**
     * Writes a portion of a string.
     *
     * @param  str  String to be written
     * @param  off  Offset from which to start reading characters
     * @param  len  Number of characters to be written
     *
     * @exception  IOException  If an I/O error occurs
     */
    @Override
    public void write(String str, int off, int len) throws IOException {
        String[] tab = Utils.getNextLine(str.substring(off, off + len));
        boolean noNewLineChar = false;

        while (true) {
            if (tab[0].length() != 0) {
                if (!multiple) {
                    buffer += ++counter + "\t";
                }

                buffer += tab[0];
                tab = Utils.getNextLine(tab[1]);
                noNewLineChar = true;
            } else {
                if (noNewLineChar || off != 0 || !multiple) {
                    buffer += ++counter + "\t";
                }
                buffer += tab[1];
                break;
            }
        }
        out.write(buffer);
        multiple = true;
        buffer = "";
    }

    /**
     * Writes a portion of a char[].
     *
     * @param  cbuf  String to be written
     * @param  off  Offset from which to start reading characters
     * @param  len  Number of characters to be written
     *
     * @exception  IOException  If an I/O error occurs
     */
    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.write(new String(cbuf), off, len);
    }

    /**
     * Writes an int.
     * 
     * @param c int to be written
     * @throws IOException 
     */
    @Override
    public void write(int c) throws IOException {
        if (first == true) {
            if (c != '\n') {
                out.write(++counter + "\t");
                first = false;
            }
        }
        out.write(c);
        if (c == '\n' || c == '\r') {
            first = true;
        }
    }
}
