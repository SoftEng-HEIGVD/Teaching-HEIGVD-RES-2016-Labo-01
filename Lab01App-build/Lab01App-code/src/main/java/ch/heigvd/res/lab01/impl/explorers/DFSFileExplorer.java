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
 * @author Luana Martelli
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    /* Visits the directory first or the root will never be visited */
    vistor.visit(rootDirectory);

    /* If the direcroy does not exists, we are done */
    if (!rootDirectory.exists()) {
      return;
    }

    /* Creates an array of the files in the directory
     * and sorts it because with this method, we are not sure
     * that it will always lists files the same way */
      File[] files = rootDirectory.listFiles();
      Arrays.sort(files);

      /* We first check if it'a file and we visit it */
      for (File f : files) {
        if (f.isFile()) {
          vistor.visit(f);
        }
      }

      /* Then we check if there is a directory and we eventually explore it */
      for (File f : files) {
        if (f.isDirectory())
          explore(f, vistor);
      }
    }
}
