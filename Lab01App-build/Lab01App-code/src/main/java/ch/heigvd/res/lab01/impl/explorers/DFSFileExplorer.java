package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

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
        // Visit current directory/file
        visitor.visit(rootDirectory);

        File[] list = rootDirectory.listFiles();
        if (list != null) {
            // Sort files so files are visited before directories, then sorted by name
            Arrays.sort(list, new Comparator<File>() {
            @Override
            public int compare(File file, File t1) {
                if(file.isDirectory()) {
                    return t1.isDirectory() ? file.getName().compareTo(t1.getName()) : 1;
                }else {
                    return t1.isDirectory() ? -1 : file.getName().compareTo(t1.getName());
                }
            }
        });
            for (final File fileEntry : list) {
                // Explore recursively
                explore(fileEntry, visitor);
            }
        }
    }
}
