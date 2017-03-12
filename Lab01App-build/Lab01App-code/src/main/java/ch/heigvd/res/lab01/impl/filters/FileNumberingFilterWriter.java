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
 * @author Damien Carnal
 */
public class FileNumberingFilterWriter extends FilterWriter {

    private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  
  
    // Numéro de linge
    private int counter;
    private boolean firstCall;
    private boolean previousWasCR;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
        counter = 1;
        firstCall = true;
        previousWasCR = false;
    }

  @Override
    public void write(String str, int off, int len) throws IOException {
        if (str == null){
            return;
        }
        // regard si l'oofset sort du string
        if (off + len > str.length()){
            return;
        }

        String string = str.substring(off, off + len);
        char[] cbuf = string.toCharArray();
        write(cbuf, 0, cbuf.length);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        if (cbuf == null){
          return;
        }
        // Envoie des caractères individuellement
        for (char c : cbuf){
            write((int) c);
        }
    }

    @Override
    public void write(int c) throws IOException {
        String str;
        if (firstCall){
            // ajoute le numéro et le tab en début
            str = counter++ + "\t";
            super.write(str, 0, str.length());
            firstCall = false;
        }

        if (c == '\n') {
            str = "\n" + counter++ + "\t";
            previousWasCR = false;
            super.write(str, 0, str.length());
        } else if (c == '\r') {
            str = "\r";
            previousWasCR = true;
            super.write(str, 0, str.length());
        } else {
            // si c'est un \r on dois écrire le no de linge et le tab
            if (previousWasCR){
                str = counter++ + "\t";
                super.write(str, 0, str.length());
            }
            super.write(c);
            previousWasCR = false;
        }
    }

}
