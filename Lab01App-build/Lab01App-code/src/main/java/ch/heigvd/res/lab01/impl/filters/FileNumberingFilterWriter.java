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

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        int currentLine = 1;
        
        String strOut = "b";
        
        String[] tabStr = Utils.getNextLine(str);
        
        /*
        //Per line
        for (String[] tab = Utils.getNextLine(str);; tab = Utils.getNextLine(tab[1])) {
            strOut += currentLine + "\t";
            strOut += tab[0];
            
            currentLine++;
            System.out.println(currentLine);
        }*/
        
        super.write(str, off, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {

    }

    @Override
    public void write(int c) throws IOException {

    }

}
