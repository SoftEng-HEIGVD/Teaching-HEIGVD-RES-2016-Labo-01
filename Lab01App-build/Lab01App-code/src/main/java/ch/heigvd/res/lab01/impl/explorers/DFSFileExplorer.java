package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
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
  public void explore(File rootDirectory, IFileVisitor visitor) {
    File[] directory = rootDirectory.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        return file.isDirectory();
      }
    });

    File[] files = rootDirectory.listFiles(new FileFilter() {
      @Override
      public boolean accept(File file) {
        return file.isFile();
      }
    });

    visitor.visit(rootDirectory);

    if (files != null) {
      Arrays.sort(files);

      for (File f : files)
            visitor.visit(f);
    }

    if (directory != null) {
      Arrays.sort(directory);

      for (File f : directory)
        explore(f, visitor);
    }
  }
}
