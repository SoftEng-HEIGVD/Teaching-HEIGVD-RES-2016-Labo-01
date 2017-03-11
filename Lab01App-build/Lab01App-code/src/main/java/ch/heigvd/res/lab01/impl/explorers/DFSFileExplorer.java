package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Adrien Marco
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    //throw new UnsupportedOperationException("The student has not implemented this method yet.");

    //we visit the first directory
    vistor.visit(rootDirectory);

    //if it is a directory
    if(rootDirectory.isDirectory()){
      //we store the list of files/folders that are in the directory
      File[] list = rootDirectory.listFiles();
      //here we store just the folders
      ArrayList<File> listDirectories = new ArrayList<File>();

      //we take each element in the directory
      for(int i = 0; i < list.length; ++i){
        //if it s a file
        if(list[i].isFile()){
          vistor.visit(list[i]);//we visit it
        }
        //if it is a directory we put it in the directory list
        else{
          listDirectories.add(list[i]);
        }
      }

      //now we can go in all the directories (to see all the tree)
      for(int dir = 0; dir < listDirectories.size(); ++dir){
        explore(listDirectories.get(dir), vistor);
      }
    }
  }

}
