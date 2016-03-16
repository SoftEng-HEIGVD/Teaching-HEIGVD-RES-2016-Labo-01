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
      // array containing the files
      File listFiles[] = null;
      if (rootDirectory == null) 
         throw new UnsupportedOperationException("root directory doesn't exist");
      
      vistor.visit(rootDirectory);
      // checks whether the file is a directory or not.
      if (rootDirectory.isDirectory()) {
         // get the files from the root directory and checks whether the directory 
         // was empty 
         if ((listFiles = rootDirectory.listFiles()) == null) 
            throw new UnsupportedOperationException("there are no files in the root "
                                                      + "directory");
         
         for (int i = 0; i < listFiles.length; ++i) {
            if (listFiles[i].isFile()) 
               explore(listFiles[i], vistor);
         }
         for (int j = 0; j < listFiles.length; ++j) {
            if (listFiles[j].isDirectory()) 
               explore(listFiles[j], vistor);
         }

      }
   }

}
