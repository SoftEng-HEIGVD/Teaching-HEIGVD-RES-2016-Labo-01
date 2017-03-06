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
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor vistor) {
        LinkedList<File> dirs = new LinkedList();
        File[] files = rootDirectory.listFiles(); // Not sure of the order...

        if (rootDirectory != null) {
            vistor.visit(rootDirectory);
        }

        // For each element in the current directory
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) {
                    dirs.add(f); // Add it in the list if directory
                } else {
                    vistor.visit(f); // Else visit it
                }
            }

            // In the end, explore every directory in the list
            for (File f : dirs) {
                explore(f, vistor);
            }
        }
    }
}
