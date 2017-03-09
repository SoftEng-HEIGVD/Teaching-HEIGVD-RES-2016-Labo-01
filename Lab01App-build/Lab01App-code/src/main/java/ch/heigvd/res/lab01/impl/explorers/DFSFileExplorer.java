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

   public void explore(File rootDirectory, IFileVisitor vistor) {
      vistor.visit(rootDirectory); // The current directory is visited.

      // We explore if the given directory is a directory.
      if (rootDirectory.isDirectory()) {
         // We first visit each files from the current directory.
         for (File file : rootDirectory.listFiles()) {
            if (file.isFile()) {
               vistor.visit(file);
            }
         }

         // Then we explore each sub directory.
         for (File file : rootDirectory.listFiles()) {
            if (file.isDirectory())
               explore(file, vistor);
         }
      }
   }
}
