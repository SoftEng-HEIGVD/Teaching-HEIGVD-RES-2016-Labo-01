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
  private int index = 1;
  boolean passedOnce = true;
  boolean first = true;
  private char tmp;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
//    String tmp = str.substring(off, off + len);
//    String result = "";
//
//    if(passedOnce == false && index == 1) {
//      passedOnce = true;
//      result += index + "\t";
//    }
//    for(int i = 0; i < tmp.length(); i++) {
//      if (tmp.charAt(i) != '\n' && tmp.charAt(i) != '\r') {
//        result += tmp.charAt(i);
//      }
//
//      if (tmp.charAt(i) == '\r' || tmp.charAt(i) == '\n') {
//        if (i < tmp.length() - 1 && tmp.charAt(i + 1) == '\n') {
//          result += tmp.charAt(i);
//          i++;
//        }
//        index++;
//        result += tmp.charAt(i);
//        result += index + "\t";
//      }
//    }
//    out.write(result);
//
    write(str.toCharArray(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    char[] tmp = new char[len];
    System.arraycopy(cbuf, off, tmp, 0, len);

    String result = "";

//    if(index == 1) {
//      result += index + "\t";
//      index++;
//    }
//    for(int i = 0; i < tmp.length; i++) {
//      if (tmp[i] != '\n' && tmp[i] != '\r') {
//        result += tmp[i];
//      }
//
//      if (tmp[i] == '\r' || tmp[i] == '\n') {
//        if (i < tmp.length - 1 && tmp[i] == '\r' && tmp[i + 1] == '\n') {
//          result += tmp[i];
//          i++;
//        }
//        result += tmp[i];
//        result += index + "\t";
//        index++;
//      }
//    }
    for(int i = 0; i < tmp.length; i++) {
      if(tmp[i] == '\n') {
        result += tmp[i];
        result += index + "\t";
        index++;
        passedOnce = false;
      } else if(tmp[i] == '\r') {
        if(passedOnce) {
          result += index + "\t";
          index++;
        }
        result += tmp[i];
        passedOnce = true;
      } else {
        if(passedOnce) {
          result += index + "\t";
          index++;
          passedOnce = false;
        }
        result += tmp[i];
      }
    }

    out.write(result);
//    char[] tmp = new char[len];
//    System.arraycopy(cbuf, off, tmp, 0, len);
//
//    for(int i = 0; i < tmp.length; i++) {
//      write(tmp[i]);
//    }
  }

  @Override
  public void write(int c) throws IOException {
//    String result = "";
//
//    if(first == false) {
//      if(c == '\n') {
//        //passedOnce = true;
//        first = true;
//      } else if (c == '\r') {
//        tmp = '\r';
//        passedOnce = true;
//        return;
//      }
//      if(passedOnce) {
//        if(c == '\n') {
//          passedOnce = false;
//        }
//        result += '\r';
//      }
//      result += (char)c;
//    } else {
//      result += index;
//      result += "\t";
//      result += (char)c;
//      index++;
//      first = false;
//    }
//    out.write(result);

    write(new char[]{(char)c}, 0, 1);
  }

}
