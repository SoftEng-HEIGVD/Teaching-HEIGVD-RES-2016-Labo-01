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
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
     vistor.visit(rootDirectory);
     
     if(rootDirectory.isDirectory()){
         //Use   tab of fileList to store directories
        File[] fileList = rootDirectory.listFiles();
    if(fileList != null) {
      Arrays.sort(fileList);
      for(File files : fileList) {
        if (files.isDirectory()) {// it is directory
          explore(files, vistor);// we explore it
        } else {
          vistor.visit(files);
        }
      }
    }
     }
  }

}
