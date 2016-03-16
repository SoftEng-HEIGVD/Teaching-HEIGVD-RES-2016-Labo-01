package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
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
      // Visit the current directory or file
      vistor.visit(rootDirectory);
      
      //If it's a directory
      if(rootDirectory.isDirectory())
      {
          //List all the files contained in the directory
          File innerFiles[] = rootDirectory.listFiles();
          
          //Sort them for the tests
          Arrays.sort(innerFiles);

          //Visite all the inner files
          for(int i = 0; i < innerFiles.length; i++)
          {
              if(innerFiles[i].isFile())
              {
                  vistor.visit(innerFiles[i]);
              }
          }

          //Explore all the inner directories
          for(int j = 0; j < innerFiles.length; j++)
          {
              if(innerFiles[j].isDirectory())
              {
                  explore(innerFiles[j], vistor);
              }
          }         
      }     
  }
}
