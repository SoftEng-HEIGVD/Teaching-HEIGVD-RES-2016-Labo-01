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
  private boolean isNewLine;
  private int number = 1;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
    //Initialise à true pour que la première ligne
    //soit aussi numérotée
    isNewLine = true;
  }
  
  /**
   * Déprécie String en char[]
   * @author Di Pietro Adrian
   * @param str String à traiter
   * @param off offset auquel on commence le traitement
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
   * Ecrit une en-tête (conditionné) écrit le caractère
   * écrit la prochaine en-tête (conditionné)
   * @author Di Pietro Adrian
   * @param c caractère à écrire
   * @throws IOException 
   */
  @Override
  public void write(int c) throws IOException {
    
    //Si c'est une nouvelle ligne et que le caractère
    //courant n'est pas \n, alors on écrit le numéro de ligne
    if(isNewLine && c != '\n')
        out.write(String.valueOf(number++) + '\t');
    
    isNewLine = false;
    
    //Ecriture du caractère
    out.write(c);
    
    //On écrit déjà la prochaine ligne si \n
    //(nécessaire pour passer les tests)
    if(c == '\n')
        out.write(String.valueOf(number++) + '\t');
    
    //Contrôle nouvelle ligne?
    if(c == '\r')
        isNewLine = true;
    
  }

}
