package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Christopher Meier
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    vistor.visit(rootDirectory);

    if(rootDirectory.isDirectory()){
      File[] subFiles = rootDirectory.listFiles();
      ArrayList<File> subDir = new ArrayList<>();

      Arrays.sort(subFiles);

      for(File f : subFiles) {
        if(f.isDirectory()) {
          subDir.add(f);
        } else {
          explore(f, vistor);
        }
      }

      for(File f : subDir) {
        explore(f, vistor);
      }
    }
  }

}
