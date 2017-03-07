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
 * @author Loan Lassalle
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
      visitor.visit(rootDirectory);
      
      if (rootDirectory.isDirectory())
      {
          File[] files = rootDirectory.listFiles();
          ArrayList<File> subDirectories = new ArrayList<>();
          Arrays.sort(files);
          
          for (File file : files)
          {
              if (file.isDirectory())
              {
                  subDirectories.add(file);
              }
              else 
              {
                  explore(file, visitor);
              }
          }
          
          for (File file : subDirectories)
          {
              explore(file, visitor);
          }
      }
  }

}
