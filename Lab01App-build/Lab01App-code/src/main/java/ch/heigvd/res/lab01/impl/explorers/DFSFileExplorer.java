package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 * 
 * @author Olivier Liechti
 * @author Nathan Gonzalez Montes
 */
public class DFSFileExplorer implements IFileExplorer {
    
    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        // We explore first the Root Directory once
        vistor.visit(rootDirectory);
        // Then if is a directory, we create a list of the directories and files
        // and then, we sort them to visit them
        if(rootDirectory.isDirectory()){ 
            File[] directory = rootDirectory.listFiles();
            Arrays.sort(directory);
            // We first visit the files at the current directory
            for(int i = 0; i < directory.length; ++i){
                if(directory[i].isFile()){
                    vistor.visit(directory[i]);
                }
            }
            // And after, we visit the subdirectories
            for(int i = 0; i < directory.length; ++i){
                if(directory[i].isDirectory()){
                    explore(directory[i], vistor);
                }
            }
        }
    }
}
