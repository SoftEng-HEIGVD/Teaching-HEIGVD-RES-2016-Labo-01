package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * @author Myszkorowski wojciech
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
      // keeps the root file's name
      vistor.visit(rootDirectory);
      //goes through the directory
      if (rootDirectory.isDirectory()) {
          File[] files = rootDirectory.listFiles();
          ArrayList<File> NextDirectory = new ArrayList<>();
          Arrays.sort(files);
          // creates a list of all files names
          for(final File subFile : files) {
              if(subFile.isDirectory()) {
                  NextDirectory.add(subFile);
              }else {
                  if (subFile.isFile()) {
                      explore(subFile, vistor);
                  }
              }      
          }
          //exploration of root files
          for(File file :NextDirectory) {
              explore(file, vistor);
          }
    }
  }
}
