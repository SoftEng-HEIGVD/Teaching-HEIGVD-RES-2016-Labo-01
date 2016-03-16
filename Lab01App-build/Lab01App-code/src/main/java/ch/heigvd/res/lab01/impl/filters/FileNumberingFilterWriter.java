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
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private static char pre;
  private int numLine;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    numLine = 1;  
	pre = 'a';
	  
  }

  @Override
  public void write(String str, int off, int len) throws IOException { 	  
	  
	  String strTmp = str.substring(off, off+len);
	  
	  strTmp = ++numLine + '\t' + strTmp;
	  
	  for(int i = off; i<=(len+off); i++){
		  if(strTmp.charAt(i) == '\n' || strTmp.charAt(i) == '\r' || (pre == '\r' && strTmp.charAt(i) == '\n')){
			  pre = strTmp.charAt(i);
			  strTmp = strTmp.substring(0, i) + ++numLine + '\t' + strTmp.substring(i+2); 
		  }
			   
	  }
	  super.write(strTmp, 0, strTmp.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
	  String str = new String(cbuf);
	  this.write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {
	if(c == '\n')	  
	  super.write(c + 1 +'\t');
	if(c == '\r')
	  super.write(c + 1 + '\t');
  }

}
