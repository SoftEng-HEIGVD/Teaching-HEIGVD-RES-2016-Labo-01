package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Ludovic Delafontaine
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
    
    // Visit the root directory
    visitor.visit(rootDirectory);
    
    // Get all the files from the root directory
    File[] files = rootDirectory.listFiles();
    
    // Proceed if there are files
    if (files != null) {
      
      // Sort the files
      Arrays.sort(files);
      
      // Create array to store the directories
      ArrayList<File> directories = new ArrayList<File>();
      
      // Parse file by file
      for (File file : files) {
        
        // Add the directory to the directories
        if (file.isDirectory()) {
          directories.add(file);
          
        // Visit the file
        } else {
          visitor.visit(file);
        }
      }
      
      // Explore each sub-directories
      for (File directory: directories) {
          explore(directory, visitor);
      }
    }
  }
  

}
