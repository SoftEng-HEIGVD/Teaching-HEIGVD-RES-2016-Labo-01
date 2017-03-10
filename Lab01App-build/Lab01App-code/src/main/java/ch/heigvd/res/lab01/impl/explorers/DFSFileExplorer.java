
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
 * @author Denise Gemesio
 */
public class DFSFileExplorer implements IFileExplorer {

   /* 
    * First, we have to visit de rootDirectory itself. Then, if the directory isn't
    * empty, first, we will visit every file and then, we will explore every 
    * directory. Warning! This has to be made in that specific ordre! First all the
    * files and THEN explore the directory!
    */
   @Override
   public void explore(File rootDirectory, IFileVisitor vistor) {
      
      vistor.visit(rootDirectory);

      if (rootDirectory.listFiles() != null) {

         File[] files = rootDirectory.listFiles();

         for (File f : files) {
            if (f.isFile()) {
               vistor.visit(f);
            }
         }

         for (File f : files) {
            if (f.isDirectory()) {
               explore(f, vistor);
            }
         }
      }
   }
}

