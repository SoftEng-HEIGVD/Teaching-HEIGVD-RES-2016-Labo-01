package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered node
 * (file and directory). When the explorer reaches a directory, it visits all files
 * in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {
   
   @Override
   public void explore(File rootDirectory, IFileVisitor vistor) {
      
      // I had troubles passing the tests with this DFS. I thought DFS was supposed to go
      // deeper if it found a directory, and no more directory was found, visits the files. 
      // In order to pass the tests, I had to reverse that and first visit all the files 
      // before going to the next directory. Seems more a BFS than a DFS to me...
      
      // If the directory is not empty
      if (rootDirectory != null) {
         vistor.visit(rootDirectory);
            if (rootDirectory.listFiles() != null && rootDirectory.isDirectory()) {
               // Visits the files first
               for (File f : rootDirectory.listFiles()) {
                  if (f.isFile()) {
                     vistor.visit(f);
                  } 
               }
               
               // Then visits the Directory
               for (File f : rootDirectory.listFiles()) {
                  if (f.isDirectory()) {
                     this.explore(f, vistor);
                  }
               }
            }
      }
      else {
         return;
      }
   }
   
}
