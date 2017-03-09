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
   public void explore(File rootDirectory, IFileVisitor visitor) {
      
      //Even if the file doesn't exist, it should be visited...
      if (!rootDirectory.exists()) {
         visitor.visit(rootDirectory);
         return;
      }
      
      /* DFS */
      File[] childFiles = rootDirectory.listFiles();
      
      visitor.visit(rootDirectory);
      
      for (File childFile : childFiles) {
         
         //Explore each subdirectories...
         if (childFile.isDirectory()) {
            explore(childFile,visitor);
         }
      }
   }

}
