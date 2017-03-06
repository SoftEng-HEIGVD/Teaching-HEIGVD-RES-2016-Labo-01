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
 * modified by: Pascal Sekley
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
     
     // We need to invokes visitor for each encountered node
     vistor.visit(rootDirectory);
     
     if (rootDirectory.isDirectory()) {
            
            // Construction of the array list that contains all the files in a directory
            File[] listOfDirectoryFiles = rootDirectory.listFiles();
            
            //  Exploration of all files of the container
            for (File file : listOfDirectoryFiles) {
               // If it's really a file we visit it and write its path
                if (file.isFile()) {
                    vistor.visit(file);
                }
                
            }
           
            //  Exploration of all the files looking for the directories and explore them
            for (File file : listOfDirectoryFiles) {
                if (file.isDirectory()) {
                    explore(file, vistor);
                }
            }
            
        } 
  }

}
