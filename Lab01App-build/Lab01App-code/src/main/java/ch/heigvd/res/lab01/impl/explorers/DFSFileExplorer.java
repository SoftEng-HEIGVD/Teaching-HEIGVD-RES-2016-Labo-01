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

      //Check if the directory is null. Indicates end of recursion
      if (rootDirectory == null) {
         return;
      }

      //Mark the Parent diectory as visited
      vistor.visit(rootDirectory);

      /*for each element contained in the rootDirectory  determine it's nature 
       we have to distinguish two types of files : directories or files
       */
      
      //for each directory we make sure that every file is first visited then after 
      // the sub-directories are explored
      if (rootDirectory.isDirectory()){ 
         for (File file : rootDirectory.listFiles()) 
            if (!file.isDirectory()) //visit each file that is not a directory
               vistor.visit(file);
            
      for (File file : rootDirectory.listFiles()) 
         if (file.isDirectory()) //if it is a directory explore its content  
               explore(file, vistor);
            
      }
      

   }
}
