package ch.heigvd.res.lab01.impl.explorers;

import ch.heigvd.res.lab01.interfaces.IFileExplorer;
import ch.heigvd.res.lab01.interfaces.IFileVisitor;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

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

        vistor.visit(rootDirectory); //start by visited the current directory
        if (rootDirectory.listFiles() == null) { //test if the current directory has a file
            return;
        }
        LinkedList<File> subdirectories = new LinkedList<>();//save the subdirectories of a file
        File[] files = rootDirectory.listFiles(); // save list of file of current directory for sort
        Arrays.sort(files);
        for (File file : files) {
            if (file.isDirectory()) {
                subdirectories.add(file);
            } else {
                vistor.visit(file);
            }

        }
        for (File subdiretory : subdirectories) {  // recursive call for each subdirectory
            explore(subdiretory, vistor);
        }

    }

}
