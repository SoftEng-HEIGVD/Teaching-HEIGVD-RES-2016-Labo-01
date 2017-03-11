package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered node
 * (file and directory). When the explorer reaches a directory, it visits all files
 * in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {

        vistor.visit(rootDirectory);
        
        // If directory, we need to further explore
        if (rootDirectory.isDirectory()){
            File[] filesAndDirectories = rootDirectory.listFiles();
            // Sort needed because tests spawn random directories and files
            Arrays.sort(filesAndDirectories);
            
            if (filesAndDirectories != null){
                LinkedList<File> directories = new LinkedList<>();
                
                // We first explore all the file from the directory, then all 
                // the subdirectories
                for (File f : filesAndDirectories){
                    if (f.isFile()){
                        explore(f, vistor);
                    } else if (f.isDirectory()){
                        directories.add(f);
                    }
                }
                for (File f : directories){
                    explore(f, vistor);
                }
            }
        }
    }
}
