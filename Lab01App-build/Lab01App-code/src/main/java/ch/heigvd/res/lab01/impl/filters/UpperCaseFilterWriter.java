package ch.heigvd.res.lab01.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }
  
  /**
   * Déprécie String en char[]
   * @author Di Pietro Adrian
   * @param str chaîne à traiter
   * @param off position à laquelle on commence le traitement
   * @param len longueur de la chaîne à traiter
   * @throws IOException 
   */
  @Override
  public void write(String str, int off, int len) throws IOException {
    //On passe l'appel en tableau de char (dépréciation)
    write(str.toCharArray(), off, len);
  }
  
  /**
   * Passe tous les caractères de la chaîne (en tenant compte de
   * la longueur et de l'offset) dans write(int)
   * @author Di Pietro Adrian
   * @param cbuf Tableau de char à traiter
   * @param off position à laquelle on commence le traitement
   * @param len longueur de la chaîne à traiter
   * @throws IOException 
   */
  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    //On traite les caractères individuellement dans write(int)
    for(int i = off; i < len + off; i++)
        write(cbuf[i]);
  }
  
  /**
   * Ecrit le caractère en majuscule
   * @author Di Pietro Adrian
   * @param c caractère
   * @throws IOException 
   */
  @Override
  public void write(int c) throws IOException {
    //Transformation en majuscule
    out.write(Character.toUpperCase(c));
  }

}
