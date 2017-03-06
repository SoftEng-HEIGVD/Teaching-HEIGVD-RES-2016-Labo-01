/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : Labo-01
 File         : DFSFileExplorer.java
 Author       : Olivier Liechti, Guillaume Serneels
 Date         : 13.03.2016
 But          : File Explorer for the quote fetching and treatment application
 -----------------------------------------------------------------------------------
*/
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
  public void explore(File rootDirectory, IFileVisitor visitor) {
      //Visit the directory in order to apply the desired treatment
      visitor.visit(rootDirectory);
      //if our root directory doesn'exist, there is nothing more to do
      if(!rootDirectory.exists())
          return;
      //List and sort every file/directory of the root directory
      File[] files = rootDirectory.listFiles();
      Arrays.sort(files);
      //Files get visited immediately
      for(File f :files)
          if(!f.isDirectory())
              visitor.visit(f);
      //Directories get explored 
      for(File f :files)
          if(f.isDirectory())
              explore(f, visitor);  //Recursive call
          
  }

}
