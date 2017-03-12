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
    private char previousCharacter = '\0';
    private int lineNum = 0;

    public FileNumberingFilterWriter(Writer out) {
        super(out);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {

        StringBuffer textFormat = new StringBuffer("");  //save the text to the right format
        String[] remainText = Utils.getNextLine(str.substring(off, off + len));
        if (lineNum == 0) {
            textFormat.append((++lineNum) + "\t");
        }
        while (!(remainText[0].isEmpty())) {
            textFormat.append(remainText[0] + (++lineNum) + "\t");// format the text
            remainText = Utils.getNextLine(remainText[1]);
        }
        textFormat.append(remainText[1]);
        String result = textFormat.toString();
        super.write(result, 0, textFormat.length());

    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        this.write(new String(cbuf), off, len);

    }

    @Override
    public void write(int c) throws IOException {
        if (lineNum == 0) {
            lineNum = 1;
            super.write('1');
            super.write('\t');
        }

        switch (c) {
            case '\n':
                if (previousCharacter != '\r') {
                    super.write('\n');
                    lineNum++;
                    String s = Integer.toString(lineNum);
                    for (int i = 0; i < s.length(); ++i) {
                        super.write(s.charAt(i));
                    }
                    super.write('\t');
                } else {
                    super.write('\n');
                    lineNum++;
                    String s = Integer.toString(lineNum);
                    for (int i = 0; i < s.length(); ++i) {
                        super.write(s.charAt(i));
                    }
                    super.write('\t');
                }
                break;
            default:
                if (previousCharacter == '\r') {
                    lineNum++;
                    String s = Integer.toString(lineNum);
                    for (int i = 0; i < s.length(); ++i) {
                        super.write(s.charAt(i));
                    }
                    super.write('\t');
                }
                super.write(c);
        }
        previousCharacter = (char) c;
    }

}
