package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;

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


  /*
    It wasn't easy to implement this part beacause we dont see DFS the same way:
    I see the file system with file and dir equally, beeing a dir is just a file with
    specific attributes and a pointer to the next list of file. So, I implemented my
    DFS taking file and dir as nodes. I finally saw your exemple of DFS :

    ./workspace/quotes
    ./workspace/quotes/A
    ./workspace/quotes/A/file1.txt
    ./workspace/quotes/A/file2.txt
    ./workspace/quotes/A/B
    ./workspace/quotes/A/C
    ./workspace/quotes/A/C/file1.txt
    ./workspace/quotes/A/C/D
    ./workspace/quotes/A/C/D/file1.txt
    ./workspace/quotes/A/C/D/file2.txt

    Mine was :

    .\workspace\quotes
    .\workspace\quotes\A
    .\workspace\quotes\A\B
    .\workspace\quotes\A\C
    .\workspace\quotes\A\C\D
    .\workspace\quotes\A\C\D\file1.txt
    .\workspace\quotes\A\C\D\file2.txt
    .\workspace\quotes\A\C\file1.txt
    .\workspace\quotes\A\file1.txt
    .\workspace\quotes\A\file2.txt

    my old code :

  public void explore(File rootDirectory, IFileVisitor vistor) {
    vistor.visit(rootDirectory);
    if(rootDirectory.isDirectory())
    {
      for(File fileInDir : rootDirectory.listFiles())
      {
        explore(fileInDir, vistor);
      }
    }
  }

  The new code is more complex


  As f character from "file1" is higher than B or C the DFS goes into B and C first.
  It would be kind to specify that you have a kind of priority on file in DFS for the
  next year students.
 */

  @Override
  public void explore(File rootDirectory, IFileVisitor vistor) {
    vistor.visit(rootDirectory);
    if(rootDirectory.isDirectory())
    {
      //Browse files (with filter)
      for(File fileInDir : rootDirectory.listFiles(new FileFilter() {
        @Override
        public boolean accept(File file) {
          return file.isFile();
        }
      }))
      {
        vistor.visit(fileInDir);
      }

      //Then browse dirs
      for(File dirInDir : rootDirectory.listFiles(new FileFilter() {
        @Override
        public boolean accept(File file) {
          return file.isDirectory();
        }
      }))
      {
        explore(dirInDir, vistor);
      }
    }
  }
}
