package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
      
      //Visit the first directory
      vistor.visit(rootDirectory);
      if(rootDirectory.isDirectory())
      {
          //Store the list of folders and files in the directory
          File[] listFile = rootDirectory.listFiles();

          //Array used only for store folders
          ArrayList<File> listDirectory = new ArrayList<File>();      

          //For each element in the directory
          for(int i = 0; i < listFile.length ; i ++)
          {         
              //if it is a file, we visit the file 
              if(listFile[i].isFile())
              {
                  vistor.visit(listFile[i]);
              }
              else
              {
                  listDirectory.add(listFile[i]);
              }
          }

          //Go in each directory
          for(int j = 0; j < listDirectory.size() ; j ++)
          {
              explore(listDirectory.get(j),vistor);

          }
      }
  }

}
