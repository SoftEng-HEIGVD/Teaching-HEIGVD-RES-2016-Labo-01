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
 * @author Luca Sivillica
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    File[] nodesCurrentDirectory = rootDirectory.listFiles();

    /* Sorting of files and directories in lexicographic order */
    if (nodesCurrentDirectory != null) {
      Arrays.sort(nodesCurrentDirectory);
    }

    vistor.visit(rootDirectory);

    if (nodesCurrentDirectory != null) {

      /* Before we visit the files in the current directory */
      for (File currentNode : nodesCurrentDirectory) {

        if (!currentNode.isDirectory())
          vistor.visit(currentNode);
      }

      /* And after we visit the sub-directories in the current directory */
      for (File currentNode : nodesCurrentDirectory) {

        if (currentNode.isDirectory()) {
          explore(currentNode, vistor);
        }
      }
    }
  }

}
