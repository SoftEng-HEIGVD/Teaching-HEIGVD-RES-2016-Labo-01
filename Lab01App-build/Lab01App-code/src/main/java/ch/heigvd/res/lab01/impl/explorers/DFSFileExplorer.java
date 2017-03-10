package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Pierre-Benjamin Monaco
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    vistor.visit(rootDirectory);
    if(rootDirectory.isDirectory())
    {
      //Get a list of files, sort it and display files
      File[] files = rootDirectory.listFiles(new FileFilter() {
        @Override
        public boolean accept(File file) {
          return file.isFile();
        }
      });
      Arrays.sort(files);
      for(File fileInDir : files)
      {
        vistor.visit(fileInDir);
      }

      //Get a list of directories, sort it and browse dirs
      File[] directories = rootDirectory.listFiles(new FileFilter() {
        @Override
        public boolean accept(File file) {
          return file.isDirectory();
        }
      });
      Arrays.sort(directories);
      for(File dirInDir : directories)
      {
        explore(dirInDir, vistor);
      }
    }
  }
}
