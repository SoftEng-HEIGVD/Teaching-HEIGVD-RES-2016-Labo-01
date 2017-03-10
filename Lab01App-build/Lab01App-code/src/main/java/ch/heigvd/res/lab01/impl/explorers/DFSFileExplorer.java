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
  public void explore(File rootDirectory, IFileVisitor vistor) {

    //Add the node
    vistor.visit(rootDirectory);

    //List of the all the files in the directory
    File[] files = rootDirectory.listFiles();

    //if the root  is a directory
    if (rootDirectory.isDirectory())
    {
      if(files !=null)
      {
        //Check first the files
        for (File f : files) {

          if(f.isFile()) {
            explore(f, vistor);
          }
        }
        //Check the directories
        for (File f : files) {

          if(f.isDirectory()) {
            //Visit the first node to have the DFS algo
            explore(f, vistor);
          }
        }
      }
    }
  }
}