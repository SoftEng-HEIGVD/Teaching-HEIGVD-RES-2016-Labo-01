package ch.heigvd.res.lab01.impl.transformers;

import java.io.Writer;

/**
 * This class returns a writer without any decorator. When an instance of
 * this class is passed to a file system explorer, it will simply duplicate
 * the content of the input file into the output file.
 * 
 * @author Olivier Liechti
 * @author Nathan Gonzalez Montes
 */
public class NoOpFileTransformer extends FileTransformer {
  /**
   * Method that returns a writer without decorators
   * @param writer the writer returned
   * @return writer without possible decorators
   */
  @Override
  public Writer decorateWithFilters(Writer writer) {
    return writer;
  }

}
