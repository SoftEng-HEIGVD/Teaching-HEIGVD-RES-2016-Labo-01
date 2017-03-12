package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
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
  private int lineNumber;
  boolean newline = true;
  public FileNumberingFilterWriter(Writer out) {
    super(out);
    lineNumber = 1;
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
	  String newStr = str.substring(off, off+len);
	  
	  String text = "";
	  String[] split = Utils.getNextLine(newStr);
	  System.out.println("-----------start----------------");
	  System.out.println("newline is " + newline);
	  System.out.println("ligne : " + split[0]);
		System.out.println("reste : " + split[1]);
		 System.out.println("-----------while----------------");
	  while (!split[0].equals("")) {
		if(newline){
			  text += lineNumber + "\t" + split[0];
			  lineNumber++;
		  }
		  else
		  {
			  text += split[0];
			  newline = true;
		  }
			
			split = Utils.getNextLine(split[1]);
			System.out.println("ligne : " + split[0]);
			System.out.println("reste : " + split[1]);
		}
	  System.out.println("-----------end of while----------------");
	  if(newline)
	  {
		  text += lineNumber + "\t";
		  lineNumber++;
	  }
	  text += split[1];
	  if(split[1].equals("") 
			  || !split[1].substring(split[1].length()-1).equals("\r")
			  || !split[1].substring(split[1].length()-1).equals("\n")
			  || !split[1].substring(split[1].length()-2).equals("\r\n"))
	  {
		newline = false;
	  }
	  
	  System.out.println(text);
	  super.write(text,0,text.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
	  String temp = String .valueOf(cbuf);
	  write(temp, off, len);
  }

  @Override
  public void write(int c) throws IOException {
	  String temp = String.valueOf((char) c);
	  write(temp,0,1);
  }

}
