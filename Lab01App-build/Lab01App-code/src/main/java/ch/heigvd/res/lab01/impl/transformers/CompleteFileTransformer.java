package ch.heigvd.res.lab01.impl.transformers;

import ch.heigvd.res.lab01.impl.filters.FileNumberingFilterWriter;
import ch.heigvd.res.lab01.impl.filters.UpperCaseFilterWriter;

import java.io.Writer;

/**
 * This class returns a writer decorated with two filters: an instance of
 * the UpperCaseFilterWriter and an instance of the FileNumberingFilterWriter.
 * When an instance of this class is passed to a file system explorer, it will
 * generate an output file with 1) uppercase letters and 2) line numbers at the
 * beginning of each line.
 * 
 * @author Olivier Liechti
 * @modifiedBy Sebastien Boson
 */
public class CompleteFileTransformer extends FileTransformer {

  /**
   * This method will decorate with specific filters the writer.
   * The first filter will convert the content of the writer to uppercase.
   * The second filter will add a line number and a \t for all lines in the writer.
   *
   * @param writer the writer that we bill decorated
   * @return the writer decorated
   */
  @Override
  public Writer decorateWithFilters(Writer writer) {
    /*
     * If you uncomment the following line (and get rid of th 3 previous lines...), you will restore the decoration 
     * of the writer (connected to the file. You can see that you first decorate the writer with an UpperCaseFilterWriter, which you then
     * decorate with a FileNumberingFilterWriter. The resulting writer is used by the abstract class to write the characters read from the
     * input files. So, the input is first prefixed with line numbers, then transformed to uppercase, then sent to the output file.f
     */
    writer = new FileNumberingFilterWriter(new UpperCaseFilterWriter(writer));
    return writer; 
  }

}
