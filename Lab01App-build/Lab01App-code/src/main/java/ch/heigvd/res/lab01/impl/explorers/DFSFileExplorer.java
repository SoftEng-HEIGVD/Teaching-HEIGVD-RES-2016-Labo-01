package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;

import java.util.ArrayList;
import java.util.List;

/**
 * This implementation of the IFileExplorer interface performs a depth-first
 * exploration of the file system and invokes the visitor for every encountered
 * node (file and directory). When the explorer reaches a directory, it visits all
 * files in the directory and then moves into the subdirectories.
 *
 * @author Olivier Liechti
 * @author Lucas Elisei (@faku99)
 */
public class DFSFileExplorer implements IFileExplorer {

    @Override
    public void explore(File rootDirectory, IFileVisitor visitor) {
        // We visit the current directory.
        visitor.visit(rootDirectory);

        // We check that the files list is not null.
        File[] filesList = rootDirectory.listFiles();
        if (filesList == null) {
            return;
        }

        // We store the directories so we can process them later.
        List<File> directories = new ArrayList<File>();

        // Then, we iterate over all the files in the current directory.
        // We must do it this way so we are sure that we process the files before
        // sub-folders because the behaviour of `File.listFiles()` can change
        // depending of the operating system.
        for (File file : filesList) {
            // If the file is indeed a file, we let the visitor visit it.
            if (file.isFile()) {
                visitor.visit(file);
            }
            // If the file is a directory, we add it to the list.
            else {
                directories.add(file);
            }
        }

        // After processing the files, we process the directories.
        for (File directory : directories) {
            explore(directory, visitor);
        }
    }

}
