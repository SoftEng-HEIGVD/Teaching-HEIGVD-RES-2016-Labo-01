package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import com.sun.xml.internal.bind.v2.TODO;

import java.io.File;
import java.util.Arrays;

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
     // Visit the directory itself
     vistor.visit(rootDirectory);

     // If the file is a directory
     if (rootDirectory.isDirectory())
     {
        // Create an array with all files and directories present in this directory
        File[] files = rootDirectory.listFiles();

        // Sort the list
        Arrays.sort(files);

        // Visit each file in the array
        for (File file : files)
        {
           if(file.isDirectory())
           {
              explore(file, vistor);
           }
           else
              vistor.visit(file);
        }
     }

  }
}
