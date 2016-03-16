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
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

   private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
   private boolean endOfLine = true;
   private int line = 0;

   public FileNumberingFilterWriter(Writer out) {
	  super(out);
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
	  write(str.toCharArray(), off, len);
   }

   @Override
   public void write(char[] cbuf, int off, int len) throws IOException {
	  final int MAX = Math.min(cbuf.length, off + len);
	  for (int i = off; i < MAX; ++i) {
		 write(cbuf[i]);
	  }
   }

   @Override
   public void write(int c) throws IOException {
	  if ((endOfLine && c != '\n') || line == 0) {
		 out.write(String.format("%d\t", ++line));
		 endOfLine = false;
	  }

	  // We write the char
	  out.write(c);

	  // We check if we aren't at the end of a line
	  if (c == '\r') {
		 endOfLine = true;
	  } else if (c == '\n') {
		 out.write(String.format("%d\t", ++line));
		 endOfLine = false;
	  }
   }
}
