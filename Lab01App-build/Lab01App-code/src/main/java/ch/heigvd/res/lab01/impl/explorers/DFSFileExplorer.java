package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import org.apache.commons.io.filefilter.DirectoryFileFilter;
import org.apache.commons.io.filefilter.FileFileFilter;

import java.io.File;
import java.io.FileFilter;

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
        // always visit
        visitor.visit(rootDirectory);

        // if node is a directory
        if (rootDirectory.isDirectory()) {
            // explore the files first
            for (File f : rootDirectory.listFiles((FileFilter) FileFileFilter.FILE)) {
                explore(f, visitor);
            }

            // explore the folders
            for (File f : rootDirectory.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY)) {
                explore(f, visitor);
            }
        }
    }
}
