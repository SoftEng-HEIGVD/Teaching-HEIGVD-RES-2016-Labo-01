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
 * @author Dany Simo
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {

      // if the directory is not empty
      if (rootDirectory != null) {
          // visits the directory
         vistor.visit(rootDirectory);
            // if the directory have files or is a directory
            if (rootDirectory.listFiles() != null && rootDirectory.isDirectory()) {               
               // visits the files
               for (File f : rootDirectory.listFiles()) {
                  if (f.isFile()) {
                     vistor.visit(f);
                  } 
               }
               
               // visits the sub-Directory
               for (File f : rootDirectory.listFiles()) {
                  if (f.isDirectory()) {
                     this.explore(f, vistor);
                  }
               }
        
            }
       }
    }
}


