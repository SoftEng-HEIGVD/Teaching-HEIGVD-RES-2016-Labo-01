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

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {
        // Use a stack to store directories for DFS
        Stack<File> stack = new Stack<>();
        stack.push(rootDirectory);

        while(!stack.empty()) {
            File dir = stack.pop();
            
            visitor.visit(dir);
            
            // List all files of the element poped from the stack
            // and visit them if they exist.
            File[] fileAndDirList = dir.listFiles();
            if(fileAndDirList != null) {
                // 
                for(int i = fileAndDirList.length-1 ; i >= 0 ; i--) {
                    if(fileAndDirList[i].isDirectory())
                        stack.push(fileAndDirList[i]);

                    visitor.visit(fileAndDirList[i]);
                }
            }
        }
  }

}
