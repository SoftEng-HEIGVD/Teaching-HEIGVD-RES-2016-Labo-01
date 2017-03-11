package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.ArrayList;

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
      
      vistor.visit(rootDirectory);
      exploreDir(rootDirectory, vistor);
   }
   
   private void exploreDir(File rootDirectory, IFileVisitor vistor) {
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
      
      // Sort the given list of files
      Arrays.sort(listOfFiles);
      
      ArrayList<File> dirs = new ArrayList<>();

      // For each file in the directory, visit the file, and explore further
      for (File file : listOfFiles) {
         if ( file.isDirectory() ) {
            dirs.add(file);
         } else {
            vistor.visit(file);
         }
      }
      
      for (File dir : dirs) {
         vistor.visit(dir);
         exploreDir(dir, vistor);
      }
   }

}
