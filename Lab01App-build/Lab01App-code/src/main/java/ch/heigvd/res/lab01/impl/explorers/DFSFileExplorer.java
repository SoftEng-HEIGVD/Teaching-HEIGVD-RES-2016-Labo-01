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
 * @author Loan Lassalle
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
      // Saves the current file's name
      visitor.visit(rootDirectory);
      
      // Explores the directory
      if (rootDirectory.isDirectory())
      {
          File[] files = rootDirectory.listFiles();
          ArrayList<File> subDirectories = new ArrayList<>();
          Arrays.sort(files);
          
          // Lists subDirectories and saves file' names
          for (File file : files)
          {
              if (file.isDirectory())
              {
                  subDirectories.add(file);
              }
              else 
              {
                  explore(file, visitor);
              }
          }
          
          // Explores subDirectories
          for (File file : subDirectories)
          {
              explore(file, visitor);
          }
      }
  }

}
