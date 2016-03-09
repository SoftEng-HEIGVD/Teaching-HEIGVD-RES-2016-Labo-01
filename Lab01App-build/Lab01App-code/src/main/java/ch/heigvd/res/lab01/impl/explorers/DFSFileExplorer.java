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
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    File[] files;
    visitor.visit(rootDirectory);
    if (rootDirectory.isDirectory()) {
      files = rootDirectory.listFiles();
      for (File f: files) // Explore all the files first
        if(f.isFile())
          explore(f,visitor);
      for (File f: files) // Explore all the subdirectories then
        if(f.isDirectory())
          explore(f,visitor);
    }
    /*
     To match the theApplicationShouldBeAbleToGenerateTheListOfFileNames test, we have to guarantee that files
     are visited before the subdirectories in a given directory. We therefore need to separate the listFiles() in two
     groups: files and directories, which is done with the two for loops.
      */


  }

}
