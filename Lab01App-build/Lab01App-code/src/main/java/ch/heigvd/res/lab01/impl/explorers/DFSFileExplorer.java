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
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {
     if(rootDirectory == null){
        return;
     }
     
     //First, visit the current directory
     visitor.visit(rootDirectory);
     
     if(rootDirectory.isDirectory()){
        //Look into the current directory
         if(rootDirectory.listFiles().length != 0){
            //The files need to be visited
            for(File f : rootDirectory.listFiles()){
               if(f.isFile()){
                  visitor.visit(f);
               }
            }
            //The directories need to be explored
            for(File f : rootDirectory.listFiles()){
               if(f.isDirectory()){
                  explore(f, visitor);
               }
            }
         }
     }
  }

}
