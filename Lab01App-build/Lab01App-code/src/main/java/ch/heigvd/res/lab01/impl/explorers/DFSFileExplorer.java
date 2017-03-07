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
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
      if(rootDirectory == null){
          throw new UnsupportedOperationException("Root directory doesn't exist !");
      }

      // Visit current node
      vistor.visit(rootDirectory);

      // check if the file is a directory
      if(rootDirectory.isDirectory()){
          // Create table that contains file in the directory
          File[] files = rootDirectory.listFiles();

          // Create and sort table that contains the list of subdirectory
          ArrayList<File> subDirectories = new ArrayList<>();
          Arrays.sort(files);

          for(File f : files){
              if(f.isDirectory()){
                  // add directory to the list of subdirectory
                  subDirectories.add(f);
              }
              else{
                  // recursive call
                  explore(f, vistor);
              }
          }

          // Explore sub directories
          for(File f : subDirectories){
              explore(f, vistor);
          }
      }
  }

}
