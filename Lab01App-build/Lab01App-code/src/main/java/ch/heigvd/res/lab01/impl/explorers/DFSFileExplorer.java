package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * Please note that the files of a directory D are visited before exploring the first sub-directory of D.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {

    visitor.visit(rootDirectory);

    // All the files in the directory
    File[] files = rootDirectory.listFiles();

    // "files" can be null if rootDirectory is not a directory or if an I/O error occurs.
    if (files == null) {
      return;
    }

    // Store the directories to explore later
    LinkedList<File> dirs = new LinkedList<>();

    // Visit each file and add sub-directories to explore later
    for (File f : files) {
      if (f.isFile()) {
        visitor.visit(f);
      } else {
        dirs.add(f);
      }
    }

    // Explore sub-directories
    for (File f : dirs) {
      explore(f, visitor);
    }
  }

}
