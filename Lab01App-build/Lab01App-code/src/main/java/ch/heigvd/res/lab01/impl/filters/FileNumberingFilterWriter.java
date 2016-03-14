package ch.heigvd.res.lab01.impl.filters;

import ch.heigvd.res.lab01.impl.Utils;

import java.io.BufferedOutputStream;
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


  private boolean rflag = false;
  private int counter = 0;
  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String endl;
    String tmp = str.substring(off, off + len);
    String[] tmpArray = getNextLine(tmp);

    //Try to find which kind of end line the string has
    if(str.contains("\r\n")){
      endl = "\r\n";
    }
    else if(str.contains("\r")){
      endl = "\r";
    }
    else{
      endl = "\n";
    }

    //Case where it's not the first line which has been added
    if(counter > 0){
      if(tmpArray[0].contains(endl)){
        out.write(tmpArray[0] + ++counter + "\t");
      }
      else{
        out.write(tmpArray[0]);
      }
    }
    //Case where it is the first line added
    else{
      out.write(++counter +  "\t" + tmpArray[0]);
      if(tmpArray[0].contains(endl)){
        out.write(++counter + "\t");
      }
    }

    //Add all the line of the string passed in argument
    while(tmpArray[1] != ""){
      tmpArray = getNextLine(tmpArray[1]);

      if(tmpArray[0].contains(endl)){
        out.write(tmpArray[0] + ++counter + "\t");
      }
      else{
        out.write(tmpArray[0]);
      }
    }
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    String str = new String(cbuf);
    write(str, off, len);
  }

  @Override
  public void write(int c) throws IOException {

    //Write "1\t" if it's the first character
    if(counter == 0){
      out.write(++counter + "\t");
    }

    //Set a flag if a "\r" is met
    if((char) c == '\r'){
      rflag = true;
      out.write(c);
    }
    //Add the character with counter + "\t" if the character is "\n"
    else if((char) c == '\n'){
      out.write(c);
      out.write(++counter + "\t");
      rflag = false;
    }
    //If the flag is set and the character isn't "\n" then counter + "\t" is added
    else {
      if (rflag) {
        out.write(++counter + "\t");
        rflag = false;
      }
      out.write(c);
    }
  }


  //This method break a line with end lines delimiter and get the next line of a string
  //It is the same method as the getNextLine of the Utils class but a little modification
  //has been added to make it easier to use
  private static String[] getNextLine(String lines) {

    String[] array = new String[2];
    String delimiter;

    if (lines.contains("\n")) {
      delimiter = "\n";
    } else if (lines.contains("\r")) {
      delimiter = "\r";
    } else {
      //If there is no delimiter return an array with the full line and an empty string
      //It's the only modification I'have made on this method
      array[0] = lines;
      array[1] = "";
      return array;
    }
    int idx = lines.indexOf(delimiter) + 1;
    array[0] = lines.substring(0, idx);

    if (lines.length() > idx) {
      array[1] = lines.substring(idx);
    } else {
      array[1] = "";
    }

    return array;
  }
}
