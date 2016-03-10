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
        exploreNode(rootDirectory, visitor);
    }

    private void exploreNode(File node, IFileVisitor visitor) {
        // File node = new File(nodeName);

        visitor.visit(node);
        if (node.isDirectory()) {
            for (File child : node.listFiles((FileFilter) FileFileFilter.FILE)) {
                visitor.visit(child);
            }

            for (File child : node.listFiles((FileFilter) DirectoryFileFilter.DIRECTORY)) {
                exploreNode(child, visitor);
            }
        }
    }
    
}
