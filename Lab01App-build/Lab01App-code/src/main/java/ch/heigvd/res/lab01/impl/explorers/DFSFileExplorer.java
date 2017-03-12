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
 * @author Iando Rafidimalala
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
     
     /**
      * visit then explore the node even if its node is a directory
      */ 
     vistor.visit(rootDirectory);
     
     if(rootDirectory.isDirectory()){
         //Listing of all nodes in the current directory
        File[] listNodes = rootDirectory.listFiles();
        if(listNodes != null) {
            /**
             * order the nodes according to the expected output
             */
          Arrays.sort(listNodes);
          for(File node : listNodes) {
              /**
               * for each directory encountered, explore it
               */
            if (node.isDirectory()) {
              explore(node, vistor);
            } else {
              vistor.visit(node);
            }
          }
        }
     }
  }

}
