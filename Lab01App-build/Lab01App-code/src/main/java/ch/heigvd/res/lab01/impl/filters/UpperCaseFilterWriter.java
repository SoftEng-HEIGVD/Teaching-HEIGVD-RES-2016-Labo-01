package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter all encounters characters, they send it to the decorated writer.
 * They then send the upper case character.
 *
 * Hello World -> HELLO WORLD
 *
 * @author Olivier Liechti
 * @author Luca Sivillica
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {

    String strUpCase = str.substring(off, off + len).toUpperCase();

    super.write(strUpCase, 0, strUpCase.length());
  }

  @Override
  public void write(String str) throws IOException {

    write(str, 0, str.length());
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {

    for (int i = off; i < off + len; i++) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(char[] cbuf) throws IOException {

    for (int i = 0; i < cbuf.length; i++) {
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {

    super.write(Character.toUpperCase(c));
  }

}
