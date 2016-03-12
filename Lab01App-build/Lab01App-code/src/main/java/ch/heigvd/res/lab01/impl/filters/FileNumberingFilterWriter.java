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
   private boolean r = false;
   private int line = 0;

   public FileNumberingFilterWriter(Writer out) throws IOException {
	  super(out);
	  out.write(++line + "\t");
   }

   @Override
   public void write(String str, int off, int len) throws IOException {
	  final String s = str.substring(off, len + off);
	  for (int i = 0; i < s.length(); ++i) {
		 write(s.charAt(i));
	  }
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
	  char s = (char) c;

	  if (s == '\r') r = true;
	  else {
		 if (s == '\n') {
			out.write("\n" + ++line + "\t");
		 } else if (r) {
			out.write("\n" + ++line + "\t" + s);
		 } else out.write(s);
		 r = false;
	  }
   }

}
