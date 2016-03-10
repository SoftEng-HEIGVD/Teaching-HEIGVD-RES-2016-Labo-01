package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;

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
      try {
          FileFilter filterFile = new FileFilter() {
              @Override
              public boolean accept(File pathname) {
                  return pathname.isFile();
              }
          };
          FileFilter filterDirectory = new FileFilter() {
              @Override
              public boolean accept(File pathname) {
                  return pathname.isDirectory();
              }
          };
          for (File f : rootDirectory.listFiles(filterFile)) {
              explore(f, vistor);
          }
          for (File f : rootDirectory.listFiles(filterDirectory)) {
              explore(f, vistor);
          }
      }catch(NullPointerException e){
          return;
      }
  }

}
