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
     /*we mark or visit the current repertory*/
     vistor.visit(rootDirectory);
     /*if the current directory is empty we stop the exploration*/
     if(rootDirectory.listFiles() == null)
        return;
     /*we visit all the file in the directory */
     for(File file : rootDirectory.listFiles())
        if(!file.isDirectory())
           vistor.visit(file);
     /*we move in subdirectories*/
     for(File subDirectory :rootDirectory.listFiles() )
        if(subDirectory.isDirectory())
        explore(subDirectory,vistor);
           
  }

}
