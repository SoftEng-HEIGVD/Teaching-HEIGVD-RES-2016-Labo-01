package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
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
    // first we must visit the rootDirectory
    vistor.visit(rootDirectory);
    
    // then we must search all files and subdirectory of the root.
    // to do that, we must first get them, sort the so they are in an alphabet order
    // then we visit all the files first
    // then we finally visit all the sub-directories
    
    //cas if the rootDirectory is not a Directory or is empty
    if (rootDirectory.isDirectory() && rootDirectory.listFiles() != null){
      //we get all the files and sub-directories
      File[] files = rootDirectory.listFiles();
      Arrays.sort(files);
      
      // first we explore all the files
      for(File f : files){
        if(f.isFile()){
          explore(f, vistor);
          }
      }
        
      // then all the directories
      for(File f : files){
        if(f.isDirectory()){
          explore(f, vistor);
        }
      }
    }
  }
}
