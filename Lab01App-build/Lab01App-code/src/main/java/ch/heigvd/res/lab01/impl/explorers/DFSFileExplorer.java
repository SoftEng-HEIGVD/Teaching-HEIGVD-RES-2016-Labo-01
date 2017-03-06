package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.Stack;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  /**
   * Perform an iterative depth-first exploration of the file system,
   * from the rootDirectory provided.
   */
  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {

    // Stop recursion
    if (rootDirectory == null)
      return;

    // Visite the current item
    visitor.visit(rootDirectory);

    // If the current item is a directory, visit all the files inside
    // and after, explore all the directories inside
    if (rootDirectory.isDirectory()) {

      // Go throught all files and directories
      File[] files = rootDirectory.listFiles();
      if (files != null) {

        // Manually sort the childs to prevent problems during the tests
        Arrays.sort(files);

        // Vitite files
        for (File file : files)
          if (file.isFile())
            visitor.visit(file);

        // Explore directories recursively
        for (File dir : files)
          if (dir.isDirectory())
            explore(dir, visitor);
      }
    }
  }

}
