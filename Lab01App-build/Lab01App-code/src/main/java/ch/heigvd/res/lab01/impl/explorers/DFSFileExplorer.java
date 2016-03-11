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
    public void explore(File position, IFileVisitor visitor) {
        // visit
        visitor.visit(position);
        // get all subdirectories and files
        final File[] files = position.listFiles();
        // if there is files or directories
        if (files != null) {
            // for every files in this directory
            for (File child : files) {
                if (child.isFile())
                    visitor.visit(child);
            }
            // for every subdirectories
            for(File child : files) {
                if (child.isDirectory())
                    explore(child, visitor);
            }
        }
    }
}

