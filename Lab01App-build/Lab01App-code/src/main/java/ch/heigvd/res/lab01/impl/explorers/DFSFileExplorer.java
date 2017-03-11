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
 * @author Sydney Hauke
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    vistor.visit(rootDirectory);

    File[] directoryContent = rootDirectory.listFiles();

    /* If I/O error or file has been provided, can't do anything else at this point */
    if(directoryContent == null) {
      return;
    }

    Arrays.sort(directoryContent); // Explore directory content with sorted pathnames

    /* Explore files, and only then directories */
    for(File file : directoryContent) {
      if(file.isFile()) {
        vistor.visit(file);
      }
    }

    for(File file : directoryContent) {
      if(file.isDirectory()) {
        explore(file, vistor);
      }
    }
  }
}