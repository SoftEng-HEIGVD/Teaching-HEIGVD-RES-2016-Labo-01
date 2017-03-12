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
      * visit then explore the node even if this node is a directory
      */
     
      vistor.visit(rootDirectory);

      //Listing of all nodes in the current directory
      File[] listNodes = rootDirectory.listFiles();
      if (listNodes != null) {
        /**
         * order the nodes according to the expected output
         */
        Arrays.sort(listNodes);

        /**
         * visit the node then explore it 
         */          
          for (File node : listNodes) {

              if (node.isFile()) {
                  vistor.visit(node);
              }
          }

          for (File node : listNodes) {
              if (node.isDirectory()) {
                  explore(node, vistor);
              }
          }
      }
  }

}
