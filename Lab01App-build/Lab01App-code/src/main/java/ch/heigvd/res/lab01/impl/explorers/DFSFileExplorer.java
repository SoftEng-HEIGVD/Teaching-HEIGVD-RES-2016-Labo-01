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
   
   /* for each file in the rootDirectory directory, we will:
    *    - If the file is a directory, visit the directory and then call the
    *      explore() function recursively with this directory
    *    - If the fils is not a directory, visit the file
    */
   @Override
   public void explore(File rootDirectory, IFileVisitor vistor) {
      vistor.visit(rootDirectory);
      
      if (rootDirectory.listFiles() != null) {
         for (File fileEntry : rootDirectory.listFiles()) {
            if (fileEntry.isDirectory())
               explore(fileEntry, vistor);
            else
               vistor.visit(fileEntry);
          
         }
      }
   }
}
