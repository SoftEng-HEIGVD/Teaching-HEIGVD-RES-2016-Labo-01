package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;

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
   
      File[] subFiles = rootDirectory.listFiles();
      // ArrayList<File> listOfFiles = new ArrayList<File>();
      ArrayList<File> listOfDirectories = new ArrayList<File>();
      
      vistor.visit(rootDirectory);
   
      // The files are visited and the derectories explored
      if (subFiles != null) {
          for(File current : subFiles) {
              if (!current.isFile()) {
                  listOfDirectories.add(current);
                  // explore(current, vistor);
              } else {
                  vistor.visit(current);
              }
          }
      }
      
      // Then it moves onto the sub-directories
     if (!listOfDirectories.isEmpty()) {
          for(File currentDir : listOfDirectories) {
              explore(currentDir, vistor);
          }
      }
  }

}
