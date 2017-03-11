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
 * Modified by Lara Chauffoureaux on 08.03.2017
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
                
        // for this method use the same method that i described in the google form
        // to perform the exploration of the file system
        // FIX : this is not exactly a dfs, but an exploration that treats files
        //       first and then explore all sub-folders. That's why a second for
        //       loop is necessary.
        vistor.visit(rootDirectory);
        
        // Stop condition of the recursion, if we aren't in a directory, we visit
        // it and return back up directly
        if(rootDirectory.isDirectory()) {  
            
            File[] files = rootDirectory.listFiles();
            
            // i can do this because File implemented the interface comparable
            Arrays.sort(files); 
            
            for(File f : files) {
                
                // FIX : in a first time we treat only files
                if(f.isFile()) {
                 
                    vistor.visit(f); 
                }
            }
            
            for(File d : files) {
                
                // FIX : in a second time we treat directories
                if(d.isDirectory()) {
                    
                    explore(d, vistor); // recursive call on all sub-folders
                }
            }
        }
    }
}
