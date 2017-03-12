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
      vistor.visit(rootDirectory);
      if(!rootDirectory.isDirectory()){
          return;
      }
      ArrayList<File> directories = new ArrayList<>();
      
      //for every file in the root directory, visit it if it's a file,
      //otherwise add it to a list of directories
      for(File f : rootDirectory.listFiles()){
          if(f.isFile()){
              vistor.visit(f);
          }else{
              directories.add(f);
          }
      }
      //explore all directories recursively
      for (File directory : directories) {
          explore(directory, vistor);
      }
      
  }

}
