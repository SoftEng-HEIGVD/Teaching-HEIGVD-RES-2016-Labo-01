package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Collections;

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
      //throw new UnsupportedOperationException("The student has not implemented this method yet.");

      // send The current File to the IFileVisitor, to be visited§
      vistor.visit(rootDirectory);

      // List of files int the directory rootDirectory
      File[] listFiles = rootDirectory.listFiles();

      //ordering the list by lexicographical order if this isn't null
      if (listFiles != null)
      {
          for (int i = 0; i < listFiles.length - 1; i++)
          {
              if (listFiles[i].compareTo(listFiles[i + 1]) > 0)
              {
                  File temp        = listFiles[i];
                  listFiles[i]     = listFiles[i + 1];
                  listFiles[i + 1] = temp;
              }
          }
          //search the files and not the directories, and explore them
      for (int j = 0; j < listFiles.length; j++)
      {
          //if(listFiles[j].listFiles().length == 0) {
              explore(listFiles[j], vistor);
          //}
      }
      //now we explore the directories
    /*  for (int k = 0; k < listFiles.length; k++)
      {
          if(listFiles[k].listFiles().length != 0) {
              explore(listFiles[k], vistor);
          }
      }*/
      }
  }

}
