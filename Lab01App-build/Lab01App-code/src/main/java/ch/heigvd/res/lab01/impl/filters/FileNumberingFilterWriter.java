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
    long num_de_ligne = 1;
    char char_precedent;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");

        String ligne_qui_suit[] = Utils.getNextLine(str.substring(off, off + len));
        String string = "";

        if (num_de_ligne == 1) {
            string = (num_de_ligne++) + "\t";
        }

        while (!ligne_qui_suit[0].isEmpty()) {
            string += ligne_qui_suit[0] + (num_de_ligne++) + "\t";
            ligne_qui_suit = Utils.getNextLine(ligne_qui_suit[1]);
        }

        string += ligne_qui_suit[1];
        out.write(string);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        write(new String(cbuf).substring(off, off + len));
    }

    @Override
    public void write(int c) throws IOException {
        //throw new UnsupportedOperationException("The student has not implemented this method yet.");
        char charactere = Character.toChars(c)[0];

        String string = "";

        if (num_de_ligne == 1
                || char_precedent == '\n'
                || (char_precedent == '\r' && charactere != '\n')) {
            string += (num_de_ligne++) + "\t";
        }

        out.write(string + charactere);
        char_precedent = charactere;
    }
}


