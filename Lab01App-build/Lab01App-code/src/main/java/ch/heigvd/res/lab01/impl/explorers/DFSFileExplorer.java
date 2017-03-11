package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories. -> this is not DFS,
 * it should go directly to the deepest folder, list it's content, and move up. Neighbours
 * should be treated only when there is nothing more to explore under the current node.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {

    try {

      visitor.visit(rootDirectory);

      // get all the files from the directory
      File[] dirContent = rootDirectory.listFiles();
      // no alphab. sort needed, we visit files first and then move to subdirectories recursively.
      for (File file : dirContent){
        if (file.isFile() ) {
          visitor.visit(file);
        }
      }
      for (File file : dirContent){
        if (file.isDirectory() ) {
          explore(file, visitor);
        }
      }
    }
    catch (Exception e) {
      System.out.println("oh la la");
    }

  }

}
