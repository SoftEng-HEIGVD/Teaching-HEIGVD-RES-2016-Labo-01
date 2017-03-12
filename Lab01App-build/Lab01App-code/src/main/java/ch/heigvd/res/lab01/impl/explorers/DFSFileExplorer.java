package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Ludovic Richard
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    if (!rootDirectory.exists()) {
      return;
    }

    File[] listElements = rootDirectory.listFiles();
    List<File> listFiles = new ArrayList<File>();
    List<File> listDirectories = new ArrayList<File>();
    for (File f : listElements) {
      if (f.isDirectory()) {
        listDirectories.add(f);
      } else {
        listFiles.add(f);
      }
    }

    for (File f : listFiles) {
      vistor.visit(f);
    }

    for (File d : listDirectories) {
      explore(d, vistor);
    }
  }
}
