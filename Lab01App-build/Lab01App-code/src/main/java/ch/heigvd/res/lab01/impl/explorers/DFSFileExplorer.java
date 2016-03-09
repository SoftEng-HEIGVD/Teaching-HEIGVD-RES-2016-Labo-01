package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
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

  /**
   * Perform an iterative depth-first exploration of the file system,
   * from the rootDirectory provided.
   */
  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {

    // Initialize a stack to performe an iterative DFS
    Stack<File> stack = new Stack<>();
    stack.push(rootDirectory);

    // Process on each items of the stack
    while (!stack.empty()) {
      File current = stack.pop();

      // Visite the current item
      visitor.visit(current);

      // Finally, if the current item is a directory, add all its child to the stack
      // Go through the reverse result of the listFiles method to push the first alphanumeric
      // item in last on the stack. So it will be next to be treated.
      if (current.isDirectory()) {
        File[] files = current.listFiles();
        if (files != null)
          for (int i = files.length - 1; i >= 0; --i)
            stack.push(files[i]);
      }
    }

  }

}
