package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @modifiedBy Sebastien Boson
 */
public class DFSFileExplorer implements IFileExplorer {

  /**
   * This method explore all the files (directories and normal files) under the rootDirectory specified.
   * We do a DFS exploration. When we reache a directory, we apply the visit method in all files (not directories)
   * in this directory before to continue with the subdirectories. The visit method is also called for the rootDirectory.
   *
   * @param rootDirectory the rootDirectory to start the DFS exploration
   * @param visitor the IFileVisitor object to call the visit method for a specific file
   */
  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    // first call to the visit method
    visitor.visit(rootDirectory);

    // we get the files (normal files and directories) in the rootDirectory
    File[] filesArray = rootDirectory.listFiles();

    // check if filesArray is not null
    if (filesArray != null) {
      // for all the files (not directories) in the rootDirectory, we call
      // the visit method
      for (File file : filesArray) {
        if (file.isFile()) {
          visitor.visit(file);
        }
      }

      // for all the directories in the rootDirectory, we call this same method
      // (explore) recursively
      for (File file : filesArray) {
        if (file.isDirectory()) {
          explore(file, visitor);
        }
      }
    }
  }

}
