package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.io.FileFilter;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits
 * all files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {

        // visit current node
        visitor.visit(rootDirectory);
        
        if (rootDirectory.isDirectory()) {
            
            // list subfiles
            for (File subnode : rootDirectory.listFiles(onlyFilesFilter)) {
                
                // visit subfile
                visitor.visit(subnode);
            }
            
            // list subdirectories
            for (File subnode : rootDirectory.listFiles(onlyDirectoriesFilter)) {
                
                // make recursive call
                explore(subnode, visitor);
            }
        }
    }
    
    // a filefiter who returns only files
    private FileFilter onlyFilesFilter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            return file.isFile();
        }
    };
    
    // a filefiter who returns only directories
    private FileFilter onlyDirectoriesFilter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            return file.isDirectory();
        }
    };
}