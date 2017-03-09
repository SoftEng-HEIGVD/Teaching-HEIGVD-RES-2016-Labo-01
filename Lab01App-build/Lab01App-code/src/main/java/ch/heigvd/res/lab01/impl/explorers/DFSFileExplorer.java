package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

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
        visitor.visit(rootDirectory);

        File[] files = rootDirectory.listFiles();

        List<File> directories = new ArrayList<>();

        if (files == null) {
            return;
        }

        for (File f : files) {
            if (f.isDirectory()) {
                directories.add(f);
            } else {
                visitor.visit(f);
            }
        }

        for (File directory : directories) {
            explore(directory, visitor);
        }
    }

}
