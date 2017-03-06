/*
 -----------------------------------------------------------------------------------
 Course       : RES
 Laboratory   : 1
 File         : DFSFileExplorer.java
 Author       : Antoine Drabble
 Date         : 10.03.2016
 Goal         : Explore a folder in DFS and apply the visit method of a class which
                implements the IFileVisitor interface to every folder/file.
 -----------------------------------------------------------------------------------
*/
package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;

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
    public void explore(File rootDirectory, IFileVisitor vistor) {
        // Visit the root directory
        vistor.visit(rootDirectory);

        // Return if root directory doesn't exists
        if(!rootDirectory.exists()){
            return;
        }

        // Get files/directories inside this directory and sort it
        File[] files = rootDirectory.listFiles();
        Arrays.sort(files);

        // Write files
        for(File f : files){
            if(!f.isDirectory()){
                vistor.visit(f);
            }
        }

        // DFS explore directories
        for(File f : files){
            if(f.isDirectory()){
                this.explore(f, vistor);
            }
        }
    }
}
