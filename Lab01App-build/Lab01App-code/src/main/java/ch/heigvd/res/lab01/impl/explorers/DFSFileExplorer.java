package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.LinkedList;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @modifiedBy Ali Miladi
 */
public class DFSFileExplorer implements IFileExplorer {

    // Rcursively visit the file's structure each time updating the root directory in case of a folder.
    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        vistor.visit(rootDirectory);
        LinkedList<File> directories = new LinkedList<>();
        if (rootDirectory.isDirectory()) {
            File[] listOfFiles = rootDirectory.listFiles();
            if (listOfFiles != null) {
                for (int i = 0; i < listOfFiles.length; i++) {
                    if (listOfFiles[i] != null) {
                        if (listOfFiles[i].isFile())
                            vistor.visit(listOfFiles[i]);
                        else if (listOfFiles[i].isDirectory())
                            directories.add(listOfFiles[i]);
                    }
                }
            }
            for(int i = 0; i < directories.size(); i++){
                explore(directories.get(i), vistor);
            }
        }
    }
}

