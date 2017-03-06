package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Stack;
import java.util.Arrays;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    /**
     * Visit recursively all files and directories by performing a depth first 
     * search
     * @param rootDirectory is the source directory from which the exploration begins
     * @param visitor is an interface to visit files
     */
    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {

        visitor.visit(rootDirectory);

        if (rootDirectory.isDirectory()) {
            
            // List all files and directories in the actual rootDirectory
            File[] fileList = rootDirectory.listFiles();

            // Sort the list to fix the problem with order
            Arrays.sort(fileList);

            // Visit all files
            for (File file : fileList)
                if (file.isFile())
                    visitor.visit(file);

            // Explore the remaining directories in a recursive way
            for (File dir : fileList)
                if (dir.isDirectory())
                    explore(dir, visitor);
        }
    }
}
