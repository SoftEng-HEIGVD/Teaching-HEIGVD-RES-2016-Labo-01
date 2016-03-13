package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.Stack;

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
    
    // This is an iterative way to browse the folders
    Stack<File> files = new Stack<>();
    files.add(rootDirectory);

    while(!files.isEmpty()) {
      File file = files.pop();
      visitor.visit(file);
      
      if(file.isDirectory()) {
        File[] list = file.listFiles();

        // Unfortunately listFiles method gives no guarantee that the strings
        // appear in a specific order
        // TODO : Reduce the complexity
        Arrays.sort(list);
        
        for(int i = list.length - 1; i >= 0; i--) {
          files.push(list[i]);
        }
      }
    }
    
  }

}
