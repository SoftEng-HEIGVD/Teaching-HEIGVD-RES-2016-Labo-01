package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    if(null == rootDirectory) {
      return;
    }
    // we first visit the root directory
    vistor.visit(rootDirectory);

    File[] files = rootDirectory.listFiles();
    if(null == files) {
      // the rootDirectory was not a directory or we had an error
      return;
    }

    Arrays.sort(files); // this is needed for the tests as Java gives no ordering guarantees about listFiles

    //noinspection ConstantConditions
    for(File f: files) {
      if(!f.isDirectory()) {
        vistor.visit(f);
      }
    }
    //noinspection ConstantConditions
    for(File f: files) {
      if(f.isDirectory()) {
        // we don't need to visit here, as it will be done in explore()
        explore(f, vistor);
      }
    }

  }

}
