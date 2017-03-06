package ch.heigvd.res.lab01.impl.filters;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FilterWriter;
import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;
import java.util.ArrayList;
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
  private int i = 1;
  private boolean newLigne = true;
  private boolean already_return = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
	  for(int i = off; i < off + len; i++)
		  write(str.charAt(i));
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
	  for(int i = off; i < off + len; i++)
		  write(cbuf[i]);
  }

  @Override
  public void write(int c) throws IOException {
	if(newLigne){
		String num = String.valueOf(i++);
	    super.write(num, 0, num.length());
	    super.write('\t');
	    newLigne = false;
	}
	else if(already_return){
		if(c == '\n')
			super.write(c);
		String num = String.valueOf(i++);
	    super.write(num, 0, num.length());
	    super.write('\t');
	    newLigne = false;	
		if(c == '\n'){
			already_return = false;
			return;
		}
	}
    super.write(c);
	if(c == '\r'){
	    already_return = true;
	}
	else if(c == '\n' && !already_return){
		String num = String.valueOf(i++);
	    super.write(num, 0, num.length());
	    super.write('\t');
	}
	else if (already_return)
		already_return = false;
  }

}
