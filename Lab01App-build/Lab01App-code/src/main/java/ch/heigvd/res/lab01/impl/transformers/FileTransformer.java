package ch.heigvd.res.lab01.impl.transformers;

import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This abstract class implements the IFileVisitor interface and has the responsibility
 * to open an input text file, to read its content, to apply a number of transformations
 * (via filters) and to write the result in an output text file.
 * 
 * The subclasses have to implement the decorateWithFilters method, which instantiates
 * a list of filters and decorates the output writer with them.
 * 
 * @author Olivier Liechti
 * @modifiedBy Sebastien Boson
 */
public abstract class FileTransformer implements IFileVisitor {

  private static final Logger LOG = Logger.getLogger(FileTransformer.class.getName());
  private final List<FilterWriter> filters = new ArrayList<>();
  
  /**
   * The subclasses implement this method to define what transformation(s) are
   * applied when writing characters to the output writer. The visit(File file)
   * method creates an output file and creates a corresponding writer. It then
   * calls decorateWithFilters and passes the writer as argument. The method
   * wraps 0, 1 or more filter writers around the original writer and returns 
   * the result.
   * 
   * @param writer the writer connected to the output file
   * @return the writer decorated by 0, 1 or more filter writers
   */
  public abstract Writer decorateWithFilters(Writer writer);

  /**
   * This method will decorate the content of the file with the specified filters.
   *
   * @param file the file that we bill decorated
   */
  @Override
  public void visit(File file) {
    if (!file.isFile()) {
      return;
    }
    try {
      // buffer add
      Reader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
      // buffer add
      Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file.getPath()+ ".out"), "UTF-8")); // the bug fix by teacher
      writer = decorateWithFilters(writer);

      int value;

      // we read the value from the file and write it in an another file
      while ((value = reader.read()) != -1 ) {
        writer.write(value);
      }
      /*
       * There is a missing piece here: you have an input reader and an ouput writer (notice how the 
       * writer has been decorated by the concrete subclass!). You need to write a loop to read the
       * characters and write them to the writer.
       */

      reader.close();
      writer.close();
    } catch (IOException ex) {
      LOG.log(Level.SEVERE, null, ex);
    }
  }

}
