package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

   @Override
   public void explore(File rootDirectory, IFileVisitor vistor) {
      File[] listOfFiles;
      try {
         // Get root directory files list
         listOfFiles = rootDirectory.listFiles();
      } catch (NullPointerException e) {
         return;
      }
      
      // Escape from this mess if there is no file to explore.
      if (listOfFiles == null) {
         return;
      }

      // For each file in the directory, visit the file, and explore further
      for (File file : listOfFiles) {
         vistor.visit(file);
         if (file.isDirectory()) {
            explore(file, vistor);
         }
      }

   }

}
