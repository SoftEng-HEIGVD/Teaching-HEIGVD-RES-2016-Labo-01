package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor)
  {
     // If the rootDirectory exists and vistor exists
     if (rootDirectory != null && vistor != null)
     {
        File[] files = rootDirectory.listFiles();

        if (files == null || files.length == 0)
           return;

        // For each files found, execute the visit function
        for (File file : files)
        {
           vistor.visit(file);

           // If the file is a directory, go inside recursively
           if (file.isDirectory())
           {
              explore(file, vistor);
           }
        }
     }
  }
}
