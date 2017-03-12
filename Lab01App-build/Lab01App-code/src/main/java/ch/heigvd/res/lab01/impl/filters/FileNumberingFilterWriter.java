package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;
import ch.heigvd.res.lab01.impl.Utils;

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
  int line_number = 1;
    char previous_char;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

        
        String line_coming_next[] = Utils.getNextLine(str.substring(off, off + len));
        String string = "";

        if (line_number == 1) {
            string = (line_number++) + "\t";
        }

        while (!line_coming_next[0].isEmpty()) {
            string += line_coming_next[0] + (line_number++) + "\t";
            line_coming_next = Utils.getNextLine(line_coming_next[1]);
        }

        string += line_coming_next[1];
        out.write(string);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException { //writing into the char buff
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        write(new String(cbuf).substring(off, off + len));
    }

    @Override
    public void write(int c) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
char my_char = Character.toChars(c)[0];

        String string = "";

        if (line_number == 1
                || previous_char == '\n'
                || (previous_char == '\r' && my_char != '\n')) {
            string += (line_number++) + "\t";
        }

        out.write(string + my_char);
        previous_char = my_char;
    }
}


