package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.LinkedList;

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
      //visit the directory in argument and test whether it has file or subdirecty
      LinkedList<File> directories = new LinkedList<File>();
      vistor.visit(rootDirectory);
      if (rootDirectory.listFiles() == null) // if there is no file in the rootDirectory we did not do anything
      {
         return;
      }
      for (File f : rootDirectory.listFiles()) { // We visit all the file in the rootDirectory
         if (f.isDirectory()) // if one of the files is also adirectory, we explore it
         {
            directories.add(f);
         } else {
            vistor.visit(f);// else we visit the file
         }
      }

      for (File f : directories) {
         explore(f, vistor);
      }
   }
  
}
