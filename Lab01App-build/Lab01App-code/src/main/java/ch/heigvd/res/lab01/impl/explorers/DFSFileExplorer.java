package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  // This method do a dfs form the rootDirectory
  // and to a visite(file) for evry file
  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

    // Mark as visited
    vistor.visit(rootDirectory);

    // In the case of the rootDirectory is a directory
    if (rootDirectory.isDirectory())
    {
      // For the iles in the directory
      for (File f: rootDirectory.listFiles((FileFilter) FileFileFilter.FILE))
        explore(f, vistor);

      // For the directories in the directory
      for (File f: rootDirectory.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY))
        explore(f, vistor);

    }
  }

}
