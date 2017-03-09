package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered node
 * (file and directory). When the explorer reaches a directory, it visits all files
 * in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @author Gallouche
 */
public class DFSFileExplorer implements IFileExplorer {

   @Override
   public void explore(File rootDirectory, IFileVisitor vistor) {
      vistor.visit(rootDirectory);
      List<File> directories = new ArrayList<File>();
      
      if (rootDirectory.isDirectory()) {
         File[] files = rootDirectory.listFiles();
         if (files != null) {
            for (File file : files) {
               if(file.isFile())
                  explore(file, vistor);
               else
                  directories.add(file);
            }
         } else {
            System.err.println(rootDirectory + ": Can't read !");
         }
      }
      for(File dir : directories)
         explore(dir, vistor);
   }
}
