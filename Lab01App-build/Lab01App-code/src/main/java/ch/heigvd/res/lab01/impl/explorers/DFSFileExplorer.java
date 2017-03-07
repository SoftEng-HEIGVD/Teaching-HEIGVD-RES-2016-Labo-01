package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @modified Colin Lavanchy
 */
public class DFSFileExplorer implements IFileExplorer {

  @Override
  public void explore(File rootDirectory, IFileVisitor visitor) {

      //Visiting current node either way (file or directory)
      visitor.visit(rootDirectory);

      if(rootDirectory.isDirectory()){
          File[] files = rootDirectory.listFiles();

          if(files==null)
              return;

          //We declare an anonymous File comparator such as files and
          // directories are sorted alphabetically but separately
          Arrays.sort(files, new Comparator<File>() {
              @Override
              public int compare(File o1, File o2) {
                  if(o1.isFile()&&o2.isDirectory()){
                      return -1;
                  }
                  else if(o1.isDirectory()&&o2.isFile()){
                      return 1;
                  }
                  else{
                      return o1.getName().compareTo(o2.getName());
                  }
              }
          });

          //We then explore all subFiles
          for(File file:files){
              explore(file,visitor);
          }
      }
  }
}
