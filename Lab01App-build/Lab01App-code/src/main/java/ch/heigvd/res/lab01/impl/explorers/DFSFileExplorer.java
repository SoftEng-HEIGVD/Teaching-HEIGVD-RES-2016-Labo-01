package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

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
   public void explore(File rootDirectory, IFileVisitor visitor) {

      //First, we have to visit where we are
      visitor.visit(rootDirectory);

      //Even if the file doesn't exist, it should be visited...
      if (!rootDirectory.exists()) {
         return;
         
      }
      
      /*     DFS     */

      File[] childFiles = rootDirectory.listFiles();
      //Sort the array to make sure to always get the same order...
      Arrays.sort(childFiles);

      // we have to check first all the files then the directories
      for (File childFile : childFiles) {
         if (childFile.isFile()) {
            visitor.visit(childFile);
         }
      }

      //Explore each subdirectories...
      for (File childFile : childFiles) {
         if (childFile.isDirectory()) {
            explore(childFile, visitor);
         }
      }
   }
}
