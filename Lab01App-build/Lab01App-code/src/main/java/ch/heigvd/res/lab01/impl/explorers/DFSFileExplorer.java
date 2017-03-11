package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

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
    public void explore(File rootDirectory, IFileVisitor vistor) {
        vistor.visit(rootDirectory);

        if (!rootDirectory.exists() || rootDirectory.isFile()) {
            return;
        }

        //It seems that we have to order the files to pass all of the tests
        File[] files = rootDirectory.listFiles();
        Arrays.sort(files);

        //Firstly, we have to explore the files    
        for (File f : files) {
            if (f.isFile()) {
                explore(f, vistor);
            }

        }

        //Secondly, we visit the directories recursivly
        for (File f : files) {
            if (f.isDirectory()) {
                explore(f, vistor);
            }
        }

    }

}
