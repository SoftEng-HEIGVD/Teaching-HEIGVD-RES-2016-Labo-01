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
  public void explore(File rootDirectory, IFileVisitor vistor) {
     
      vistor.visit(rootDirectory);
      if ( rootDirectory.isDirectory ( ) ) {
         File[] list = rootDirectory.listFiles();
         Arrays.sort(list);
         if (list != null){
             //explore d'abord les fichiers
            for ( int i = 0; i < list.length; i++) {
                if(list[i].isFile())
                {
                    explore( list[i], vistor);
                }
            } 
            //explore ensuite les dossiers
            for ( int i = 0; i < list.length; i++) {
                if(list[i].isDirectory())
                {
                    explore( list[i], vistor);
                }
            } 
         } else {
            System.err.println(rootDirectory + " : Erreur de lecture.");
         }
      }
     
  }

}
